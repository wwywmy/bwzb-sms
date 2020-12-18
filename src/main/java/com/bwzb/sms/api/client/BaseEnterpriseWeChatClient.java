package com.bwzb.sms.api.client;

import com.bwzb.sms.api.exception.EnterpriseWeChatApiException;
import com.bwzb.sms.api.request.EnterpriseWeChatRequest;
import com.bwzb.sms.api.response.EnterpriseWeChatResponse;

public abstract interface BaseEnterpriseWeChatClient extends EnterpriseWeChatClient {
	public abstract <T extends EnterpriseWeChatResponse> T execute(
			EnterpriseWeChatRequest<T> paramEnterpriseWeChatRequest, String paramString1, String paramString2)
			throws EnterpriseWeChatApiException;

	public abstract <T extends EnterpriseWeChatResponse> T execute(
			EnterpriseWeChatRequest<T> paramEnterpriseWeChatRequest, String paramString1, String paramString2,
			String paramString3) throws EnterpriseWeChatApiException;

	public abstract <T extends EnterpriseWeChatResponse> T execute(
			EnterpriseWeChatRequest<T> paramEnterpriseWeChatRequest, String paramString1, String paramString2,
			String paramString3, String paramString4) throws EnterpriseWeChatApiException;
}
