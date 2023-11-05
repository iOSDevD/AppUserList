package com.userlist.home;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.AppError;
import com.userlist.jdbc.AppJDBCTemplate;


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
	
	@RequestMapping(value="/registerUser")
	public HashMap<String, String> registerUser(@RequestBody final User aUserObject) throws Exception{
		
		HashMap<String, String> people=new HashMap<String, String>();

		if(aUserObject.getUserFName().isEmpty()){
			throw new InvalidUserException("Missing First Name");
	
		}else if(aUserObject.getUserLName().isEmpty()){
			throw new InvalidUserException("Missing Last Name");
			
		}else{
			this.template.insertAppData(null);
			System.out.println("Temp "+template);
		}
		return people;
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
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces={"application/json"})
	public  @ResponseBody HashMap<String, String> login(
			@RequestBody final LoginUser aUserObject,
		HttpSession session) {
		//session.invalidate();
//		if (!session.isNew()) {
//			session.invalidate();
//		}
		HashMap<String, String> loginStatus=new HashMap<String, String>();
		if(aUserObject.getUsername().equalsIgnoreCase("acc1") && aUserObject.getPassword().equalsIgnoreCase("123")) {
			session.setAttribute("username", aUserObject.getUsername());
			loginStatus.put("status","success");
			return loginStatus; 
		} else {
			loginStatus.put("status","failed");
			return loginStatus; 
		}
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.POST,  produces={"application/json"})
	public @ResponseBody HashMap<String, String> logout(HttpSession session) {
		HashMap<String, String> logoutStatus=new HashMap<String, String>();
		logoutStatus.put("status","success");
		
		session.removeAttribute("username");
		session.invalidate();
		return logoutStatus;
	}
	
	
//	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(InvalidUserException.class)
//	@ResponseBody AppError handleProductException(HttpServletRequest req,InvalidUserException ex){
//		return new AppError(req.getRequestURI()+" Invalid Product", ex);
//	}
	
	
	 
	
	
}
