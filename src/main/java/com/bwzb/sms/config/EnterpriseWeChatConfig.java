package com.bwzb.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.bwzb.sms.api.client.DefaultEnterpriseWeChatClient;
import com.bwzb.sms.api.client.EnterpriseWeChatClient;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class EnterpriseWeChatConfig {
	private String webhook;
	
	@Bean
	public EnterpriseWeChatClient getEnterpriseWeChatClient() {
		return new DefaultEnterpriseWeChatClient(webhook);
	}
}
