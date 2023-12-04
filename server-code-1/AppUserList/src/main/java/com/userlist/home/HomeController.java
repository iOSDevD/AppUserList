package com.userlist.home;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.userlist.jdbc.AppJDBCTemplate;
import com.userlist.jwt.AppJWTParserUtil;
import com.userlist.pojos.AccountDetails;


/**
 * @author Nikunj.Upadhyay
 * 
 * CS-763 Project - AppUserList
 * 
 * Main REST Controller for the project that handles login, logout
 * and fetching User list.
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private AppJDBCTemplate template;
	
	@RequestMapping(value="/addNewUser")
	public HashMap<String, String> registerUser(@RequestBody final User aUserObject) throws Exception{

		HashMap<String, String> response=new HashMap<String, String>();

		if(aUserObject.getUserFName().isEmpty()){
			throw new InvalidUserException("Missing First Name");
	
		}else if(aUserObject.getUserLName().isEmpty()){
			throw new InvalidUserException("Missing Last Name");
			
		}else{
			boolean added = this.template.insertUser(aUserObject);
			if(added) {
				response.put("status", "success");
			} else {
				response.put("status", "failed");
			}
			System.out.println("Temp "+template);
		}
		return response;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/appUsers")
	public HashMap<String, String> home(Locale locale,HttpServletRequest req ,Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		HashMap<String, String> responseData=new HashMap<String, String>();
		responseData.put("status", "Connected");
		return responseData;
	}
	
	@RequestMapping(value = "/fetchAppUsers")
	public @ResponseBody ResponseEntity<List<User>> fetchAppUsers(Locale locale,HttpServletRequest req ,
			HttpSession session,
			@RequestHeader(value = "Authorization", required = false) String token,
			Model model) {
		logger.info("Fetching all App Users");
		DecodedJWT jwtToken;
		try {
			

			jwtToken = validateSessionBasedToken(token, session);
			
			if(jwtToken != null) {
				
				
				return ResponseEntity
				.ok()
				.body(this.template.fetchAllUsers());
			} else {
				return ResponseEntity
				.badRequest()
				.body(new ArrayList<User>());
			}
		} catch (Exception e) {
			logger.warn("Invalid token request found for fetchAppUsers");
			return ResponseEntity
					.badRequest()
					.body(new ArrayList<User>());
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, produces={"application/json"})
	public  @ResponseBody ResponseEntity<HashMap<String, String>> login(
			@RequestHeader(value = "Authorization", required = false) String token,
			HttpServletResponse response,
		HttpSession session) {
		//session.invalidate();
//		if (!session.isNew()) {
//			session.invalidate();
//		}
		DecodedJWT jwtToken;
		HashMap<String, String> loginStatus=new HashMap<String, String>();
		try {
			jwtToken = validateToken(token);
			
			// Add JWT Token to Session - Only login can add this token
			HttpCookie cookieJWT = new HttpCookie("JWT", token);
			cookieJWT.setHttpOnly(true);
			
			
			AccountDetails accountDetail = this.template.validateAccountWithToken(jwtToken);
			if(accountDetail!=null) {
				session.setAttribute("username", accountDetail.getUserName());
				session.setAttribute("JWT", cookieJWT.toString());
				loginStatus.put("status","success");
				loginStatus.put("role",String.valueOf(accountDetail.getRole()));
				
				return ResponseEntity
				.ok()
				.header(HttpHeaders.SET_COOKIE, cookieJWT.toString())
				.body(loginStatus);
			} else {
				
				loginStatus.put("status","failed");
				
				return ResponseEntity
				.ok()
				.body(loginStatus);

			}
			
		} catch (Exception e) {
			logger.info("Invalid token request found for Login");
			loginStatus.put("status","failed");
			return ResponseEntity
					.badRequest()
					.body(loginStatus);
		}
		

	}
	
	@RequestMapping(value = "logout", method = RequestMethod.POST,  produces={"application/json"})
	public @ResponseBody HashMap<String, String> logout(@RequestHeader(value = "Authorization", required = false) String token,
			HttpSession session) {
		
		HashMap<String, String> logoutStatus=new HashMap<String, String>();
		logoutStatus.put("status","success");
		logger.info("Logging out user {} with sessionId {}",session.getAttribute("JWT"),
				session.getId());
		session.removeAttribute("JWT");
		session.invalidate();
		return logoutStatus;
	}
	
	private DecodedJWT validateToken(String token) throws Exception {
		
		AppJWTParserUtil appJWTParserUtil = new AppJWTParserUtil();
		
		DecodedJWT decodeToken = appJWTParserUtil.decodeToken(token);
		
		
		if(appJWTParserUtil.hasTokenExpired(token)) {
			throw new Exception("Request Failed = "+HttpStatus.BAD_REQUEST);
		}
		else if(!appJWTParserUtil.isValidAudience(decodeToken)) {
			throw new Exception("Request Failed = "+HttpStatus.BAD_REQUEST);
		} else {
			return decodeToken;
		}
	}
	
	private DecodedJWT validateSessionBasedToken(String requestHeaderToken, HttpSession session) throws Exception {
		
		AppJWTParserUtil appJWTParserUtil = new AppJWTParserUtil();
		
		DecodedJWT decodeToken = appJWTParserUtil.decodeToken(requestHeaderToken);
		
		
		String sessionToken = (session.getAttribute("JWT") !=null) ? session.getAttribute("JWT").toString() : "";
		  
		if (sessionToken.trim().equalsIgnoreCase("") && requestHeaderToken.compareTo(sessionToken) != 0) {
			logger.error("Session Based Token Valiation failed, tokens did not match");
			throw new Exception("Request Failed = "+HttpStatus.BAD_REQUEST);
		}
		
		return validateToken(requestHeaderToken);
	}
	
	
//	private String getCookieByName(HttpServletRequest req, String cookieName) {
//		Cookie[] cookies = req.getCookies();
//		for (Cookie cookie : cookies) {
//			if(cookie.getName().equalsIgnoreCase("JWT")) {
//				return cookie.getValue();
//			}
//		}
//		return "";
//	}
}
