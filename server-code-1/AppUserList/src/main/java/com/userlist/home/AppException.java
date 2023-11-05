package com.userlist.home;




public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMsg;
	private int errorCode;
	private String description;
	
	public AppException(String errorMsg,int erroCode,String description ){
		this.errorMsg=errorMsg;
		this.errorCode=erroCode;
		this.description=description;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
