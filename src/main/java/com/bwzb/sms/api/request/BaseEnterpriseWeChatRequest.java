package com.bwzb.sms.api.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.bwzb.sms.api.response.EnterpriseWeChatResponse;

public abstract class BaseEnterpriseWeChatRequest <T extends EnterpriseWeChatResponse> implements EnterpriseWeChatRequest<T>{

	@JSONField(serialize=false)
	public String getHttpBody() {
		return JSON.toJSONString(this);
	}
}
