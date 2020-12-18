package com.bwzb.sms.api.client;

import com.bwzb.sms.api.exception.EnterpriseWeChatApiException;
import com.bwzb.sms.api.request.EnterpriseWeChatRequest;
import com.bwzb.sms.api.response.EnterpriseWeChatResponse;

public abstract interface EnterpriseWeChatClient {

	public abstract <T extends EnterpriseWeChatResponse> T execute(
			EnterpriseWeChatRequest<T> paramEnterpriseWeChatRequest) throws EnterpriseWeChatApiException;

	public abstract <T extends EnterpriseWeChatResponse> T execute(
			EnterpriseWeChatRequest<T> paramEnterpriseWeChatRequest, String paramString)
			throws EnterpriseWeChatApiException;
}
