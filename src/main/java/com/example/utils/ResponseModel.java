package com.example.utils;

public class ResponseModel {
	public ResponseModel(int status,String msg){
		this.status=status;
		this.msg=msg;
	}
	public ResponseModel(int status,String msg,int error_code){
		this.status=status;
		this.msg=msg;
		this.error_code=error_code;
	}
	/**
	 * 状态码
	 */
	private int status;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 错误返回码
	 */
	private int error_code;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	
}