package com.hz.util;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class Result<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private String sign;
	private T data;

//	public static <T> Result<T> createBySuccess(String msg){
//		Result<T> result = new Result<>();
//		result.setCode(Constant.API_SUCC);
//		result.setMsg(msg);
//		return result;
//	}
//
//	public static <T> Result<T> createBySuccess(String msg ,T data){
//		Result<T> result = new Result<>();
//		result.setCode(Constant.API_SUCC);
//		result.setMsg(msg);
//		result.setData(data);
//		return result;
//	}
//
//	public static <T> Result<T> createByError(String msg){
//		Result<T> result = new Result<>();
//		result.setCode(Constant.API_ERROR_PARAM);
//		result.setMsg(msg);
//		return result;
//	}
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
