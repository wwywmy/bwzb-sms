package com.bwzb.sms.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.bwzb.sms.api.response.EnterpriseWeChatResponse;

public abstract interface EnterpriseWeChatRequest<T extends EnterpriseWeChatResponse> {
	@JSONField(serialize=false)
	public abstract String getApiMethodName();
	@JSONField(serialize=false)
	public abstract Class<T> getResponseClass();
	public abstract String getHttpBody();
}
