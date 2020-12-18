package com.bwzb.sms.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseWeChatApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7773962550194407456L;

	private String errCode;
	private String errMsg;
	private String subErrCode;
	private String subErrMsg;

	public EnterpriseWeChatApiException(Throwable cause) {
		super(cause);
	}
}
