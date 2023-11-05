package com.user.exception;

import com.userlist.home.InvalidUserException;

public class AppError {
    public  final String url;
    public  final String ex;

    public AppError(String url, Exception ex) {
        this.url = url;
        if(ex instanceof InvalidUserException){
        	this.ex = ((InvalidUserException)ex).getMessage();
        }else{
        	this.ex = ex.getLocalizedMessage();	
        }
        
    }
}
