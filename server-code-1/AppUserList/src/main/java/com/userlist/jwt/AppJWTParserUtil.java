package com.userlist.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.userlist.constants.PropertiesFileLoader;


public final class AppJWTParserUtil {

	private String secretKey;

	private static final Logger logger = LoggerFactory.getLogger(AppJWTParserUtil.class);

	public AppJWTParserUtil() {
		logger.info("AppJWTParserUtil initialized");
	}
	
	public DecodedJWT decodeToken(String token) {
		token = token.replace("Bearer: ", "");
		DecodedJWT decodeJWT = JWT.decode(token);
		return decodeJWT;
	}
	

	public boolean isValidAudience(DecodedJWT decodeJWT) {
		String audienceReceived = decodeJWT.getAudience().get(0);
		String audienceInPropertyFile = PropertiesFileLoader.getAudience();
		if (audienceReceived.compareTo(audienceInPropertyFile) == 0) {
			logger.info("Valid audience Access");
			return true;
		} else {
			logger.warn("Invalid audience access identified ="+audienceReceived);
			return false;
		}
	}

	public boolean hasTokenExpired(String token) {
		return false;
		/*
		 Use Secret key from Auth0 to verify the token
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			return false; // Token is not expired
		} catch (TokenExpiredException e) {
			return true; // Token has expired
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true; // Can't validate token
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true; // Can't validate token
		}*/
	}

}
