package com.bwzb.sms.api.response;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class EnterpriseWeChatResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3520234763337010458L;

	private Integer errcode;

	private String errmsg;
}
