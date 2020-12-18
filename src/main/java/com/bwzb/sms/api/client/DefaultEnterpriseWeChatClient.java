package com.bwzb.sms.api.client;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.bwzb.sms.api.EnterpriseWeChatConstants;
import com.bwzb.sms.api.exception.EnterpriseWeChatApiException;
import com.bwzb.sms.api.request.EnterpriseWeChatRequest;
import com.bwzb.sms.api.response.EnterpriseWeChatResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@RequiredArgsConstructor
@Slf4j
public class DefaultEnterpriseWeChatClient implements EnterpriseWeChatClient {

	@NonNull
	private String webhookKey;

	@Resource
	private RestTemplate restTemplate;

	@Override
	public <T extends EnterpriseWeChatResponse> T execute(EnterpriseWeChatRequest<T> paramEnterpriseWeChatRequest)
			throws EnterpriseWeChatApiException {
		return executeApi(paramEnterpriseWeChatRequest, null);
	}

	@Override
	public <T extends EnterpriseWeChatResponse> T execute(EnterpriseWeChatRequest<T> paramEnterpriseWeChatRequest,
			String paramString) throws EnterpriseWeChatApiException {
		return executeApi(paramEnterpriseWeChatRequest, paramString);
	}

	private <T extends EnterpriseWeChatResponse> T executeApi(EnterpriseWeChatRequest<T> request, String session)
			throws EnterpriseWeChatApiException {

		String serverUrl = StringUtils.join(//
				EnterpriseWeChatConstants.WEBHOOK_ENDPOINT, //
				EnterpriseWeChatConstants.WEBHOOK_ENDPOINT.indexOf("?") > 0 ? "&" : "?", //
				EnterpriseWeChatConstants.WEBHOOK_PARAM_KEY, //
				"=", //
				webhookKey//
		);
		log.info("serverUrl={}", serverUrl);

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(request.getHttpBody(), headers);
		log.info(request.getHttpBody());
//		T response = null;
//		try {
//			request.getResponseClass().newInstance();
//		} catch (Exception e) {
//			throw new EnterpriseWeChatApiException(e);
//		}

		T response = restTemplate.postForEntity(serverUrl, formEntity, request.getResponseClass()).getBody();

		log.info(JSON.toJSONString(response));

		return response;
	}

}
