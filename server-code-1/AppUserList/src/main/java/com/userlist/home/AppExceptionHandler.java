package com.userlist.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.user.exception.AppError;



@ControllerAdvice
public class AppExceptionHandler {
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = AppException.class)
	@ResponseBody
	public AppError handleBadRequest(HttpServletRequest req, AppException ex) {
		
	    return new AppError(req.getRequestURI(), ex);
	}
}
