package com.bwzb.sms.entity;

public class CommonResult<T> {
	private long code;
	private String message;
	private T data;

	protected CommonResult() {
	}

	protected CommonResult(long code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 成功返回结果
	 *
	 * @param message 提示信息
	 * @param data    获取的数据
	 */
	public static <T> CommonResult<T> success(String message, T data) {
		return new CommonResult<T>(200L, message, data);
	}

	/**
	 * 失败返回结果
	 *
	 * @param message 错误信息
	 */
	public static <T> CommonResult<T> failed(String message) {
		return new CommonResult<T>(500L, message, null);
	}

}
