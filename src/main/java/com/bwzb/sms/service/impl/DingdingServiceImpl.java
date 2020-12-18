package com.bwzb.sms.service.impl;

import java.net.URLEncoder;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.bwzb.sms.service.IDingdingService;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DingdingServiceImpl implements IDingdingService {

	@Override
	public boolean sendText(String content) {

		try {
			Long timestamp = System.currentTimeMillis();
			String secret = "SEC8a580d97a5d62330a6a6c1a1dab0ba138000d6e5dea5ba24c80d5ac71eb9bde0";
			String stringToSign = timestamp + "\n" + secret;
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
			byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
			String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
			System.out.println(sign);

//	 	   https://oapi.dingtalk.com/robot/send?access_token=XXXXXX&timestamp=XXX&sign=XXX

			String url = "https://oapi.dingtalk.com/robot/send?access_token=e42661ccd9bada2ad7387245cf42bcdbca1d0670f44a65f2344992830dd47d0b"
					+ "&timestamp=" + timestamp + "&sign=" + sign;

			DingTalkClient client = new DefaultDingTalkClient(url);

			OapiRobotSendRequest request = new OapiRobotSendRequest();
			request.setMsgtype("text");
			OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
			text.setContent(content);
			request.setText(text);
			OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
			at.setAtMobiles(Arrays.asList("13477059705"));
			// isAtAll类型如果不为Boolean，请升级至最新SDK
			at.setIsAtAll(true);
			request.setAt(at);
			
			request.setMsgtype("link");
			OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
			link.setMessageUrl("https://www.dingtalk.com/");
			link.setPicUrl("");
			link.setTitle("时代的火车向前开");
			link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
			request.setLink(link);
	
//			request.setMsgtype("markdown");
//			OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//			markdown.setTitle("杭州天气");
//			markdown.setText("#### 杭州天气 @156xxxx8827\n" +
//			        "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//			        "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
//			        "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//			request.setMarkdown(markdown);

			OapiRobotSendResponse response = client.execute(request);
			log.info("response=[{}]", response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}



		return false;
	}

}
