package com.bwzb.sms.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bwzb.sms.service.IDingdingService;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DingdingServiceImpl implements IDingdingService {
	
	@Resource
	private DingTalkClient dingTalkClient;

	@Override
	public boolean sendText(String content) {

		try {

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
			
//			request.setMsgtype("link");
//			OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//			link.setMessageUrl("https://www.dingtalk.com/");
//			link.setPicUrl("");
//			link.setTitle("时代的火车向前开");
//			link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
//			request.setLink(link);
	
//			request.setMsgtype("markdown");
//			OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//			markdown.setTitle("杭州天气");
//			markdown.setText("#### 杭州天气 @156xxxx8827\n" +
//			        "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//			        "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
//			        "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//			request.setMarkdown(markdown);
			log.info(JSON.toJSONString(request));
			OapiRobotSendResponse response = dingTalkClient.execute(request);
			log.info(JSON.toJSONString(response));

		} catch (Exception e) {
			e.printStackTrace();
		}



		return false;
	}

}
