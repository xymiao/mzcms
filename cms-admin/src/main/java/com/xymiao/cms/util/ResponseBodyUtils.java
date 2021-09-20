package com.xymiao.cms.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xymiao.cms.enums.ResponseCode;


/**
 * 返回数据工具
 *
 * @param <T> 需要处理的数据类型.
 */
public class ResponseBodyUtils<T> {

	private int rcode = 200;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer errcode;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errmsg;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	public Integer getErrcode() {
		return errcode;
	}

	public ResponseBodyUtils<T> setErrcode(Integer errcode) {
		this.errcode = errcode;
		return this;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public static <T> ResponseBodyUtils<T> createBySuccess() {
		return new ResponseBodyUtils<T>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> ResponseBodyUtils<T> createBySuccess(T data) {
		return new ResponseBodyUtils<T>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> ResponseBodyUtils<T> createBySuccessMessage(String msg) {
		return new ResponseBodyUtils<T>(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static <T> ResponseBodyUtils<T> createBySuccessCodeMessage(String msg, T data) {
		return new ResponseBodyUtils<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}


	public static <T> ResponseBodyUtils<T> createByErrorMessage(String errorMessage) {
		return new ResponseBodyUtils<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> ResponseBodyUtils<T> createByErrorCodeMessage(int erroCode, String errorMessage) {
		return new ResponseBodyUtils<T>(erroCode, errorMessage);
	}

	public static <T> ResponseBodyUtils<T> createByDataCodeMessage(T data, int erroCode, String errorMessage) {
		return new ResponseBodyUtils<T>(erroCode, errorMessage, data);
	}

	public ResponseBodyUtils<T> setErrmsg(String errmsg) {
		this.errmsg = errmsg;
		return this;
	}

	public ResponseBodyUtils<T> error(String message) {
		return error(null, message);
	}

	public ResponseBodyUtils<T> error(T data) {
		return error(data, "ERROR");
	}

	public ResponseBodyUtils<T> error(T data, String message) {
		this.data = data;
		this.message = message;
		rcode = 400;
		return this;
	}

	public ResponseBodyUtils<T> success(T data) {
		return success(data, "SUCCESS");
	}

	public ResponseBodyUtils<T> success(T data, String message) {
		this.data = data;
		this.message = message;
		rcode = 200;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResponseBodyUtils<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResponseBodyUtils<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public int getRcode() {
		return rcode;
	}

	public ResponseBodyUtils<T> setRcode(int success) {
		this.rcode = success;
		return this;
	}

	public ResponseBodyUtils() {
		super();
	}

	public ResponseBodyUtils(int rcode) {
		this.rcode = rcode;
	}

	public ResponseBodyUtils(int rcode, T data) {
		this.rcode = rcode;
		this.data = data;
	}

	public ResponseBodyUtils(int rcode, String message) {
		this.rcode = rcode;
		this.message = message;
	}

	public ResponseBodyUtils(int rcode, String message, T data) {
		this.rcode = rcode;
		this.message = message;
		this.data = data;
	}

	public ResponseBodyUtils(String message, T data) {
		this.message = message;
		this.data = data;
	}

	public ResponseBodyUtils(T data, String message, int success) {
		this.data = data;
		this.message = message;
		this.rcode = success;
	}

	public ResponseBodyUtils(T data, String message) {
		this.data = data;
		this.message = message;
		this.rcode = 200;
	}

	public ResponseBodyUtils(T data) {
		this.data = data;
		this.rcode = 200;
	}
}
