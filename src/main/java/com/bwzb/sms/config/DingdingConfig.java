package com.bwzb.sms.config;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "dingding")
@Data
public class DingdingConfig {
	private String secret;
	private String accessToken;
	
	@Bean
	public DingTalkClient getDingTalkClientt() throws Exception{
		Long timestamp = System.currentTimeMillis();
		String stringToSign = timestamp + "\n" + secret;
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
		byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
		String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
//		System.out.println(sign);

// 	   https://oapi.dingtalk.com/robot/send?access_token=XXXXXX&timestamp=XXX&sign=XXX

		String url = "https://oapi.dingtalk.com/robot/send"//
				+ "?access_token=" + accessToken//
				+ "&timestamp=" //
				+ timestamp //
				+ "&sign=" + sign//
		;

		DingTalkClient client = new DefaultDingTalkClient(url);
		
		return client;
	}
}
