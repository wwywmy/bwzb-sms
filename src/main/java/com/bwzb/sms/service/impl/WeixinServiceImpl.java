package com.bwzb.sms.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bwzb.sms.api.MsgTypeEnum;
import com.bwzb.sms.api.client.EnterpriseWeChatClient;
import com.bwzb.sms.api.exception.EnterpriseWeChatApiException;
import com.bwzb.sms.api.request.EnterpriseWeChatRobotSendRequest;
import com.bwzb.sms.api.response.EnterpriseWeChatRobotSendResponse;
import com.bwzb.sms.entity.weixin.NewArticle;
import com.bwzb.sms.service.IWeixinService;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeixinServiceImpl implements IWeixinService {

	@Autowired
	private EnterpriseWeChatClient client;

	@Override
	public boolean sendText(String content) {
		return sendText(content, null, null);
	}

	@Override
	public boolean sendText(String content, List<String> mentionedList) {
		return sendText(content, mentionedList, null);
	}

	@Override
	public boolean sendText(String content, List<String> mentionedList, List<String> mentionedMobileList) {

		EnterpriseWeChatRobotSendRequest request = new EnterpriseWeChatRobotSendRequest();
		request.setMsgType(MsgTypeEnum.text);

		EnterpriseWeChatRobotSendRequest.Text text = new EnterpriseWeChatRobotSendRequest.Text();
		text.setContent(content);
		text.setMentionedList(mentionedList);
		text.setMentionedMobileList(mentionedMobileList);

		request.setText(text);

		return send(request);

	}

	@Override
	public boolean sendMarkdown(String content) {
		EnterpriseWeChatRobotSendRequest request = new EnterpriseWeChatRobotSendRequest();
		request.setMsgType(MsgTypeEnum.markdown);

		EnterpriseWeChatRobotSendRequest.Markdown markdown = new EnterpriseWeChatRobotSendRequest.Markdown();
		markdown.setContent(content);

		request.setMarkdown(markdown);

		return send(request);
	}

	@Override
	public boolean sendImage(byte[] source) {
		String base64 = Base64.encode(source);
		String md5 = MD5.create().digestHex(source);

		EnterpriseWeChatRobotSendRequest request = new EnterpriseWeChatRobotSendRequest();
		request.setMsgType(MsgTypeEnum.image);

		EnterpriseWeChatRobotSendRequest.Image image = new EnterpriseWeChatRobotSendRequest.Image();
		image.setBase64(base64);
		image.setMd5(md5);

		request.setImage(image);

		return send(request);
	}
	
	@Override
	public boolean sendImage(File file) {
		return sendImage(FileUtil.readBytes(file));
	}
	
	@Override
	public boolean sendImage(InputStream in) {
		return sendImage(IoUtil.readBytes(in));
	}
	
	@Override
	public boolean sendNews(List<NewArticle> newArticleList) {
		String jsonString = JSON.toJSONString(newArticleList);
		log.info(jsonString);
		
		EnterpriseWeChatRobotSendRequest request = new EnterpriseWeChatRobotSendRequest();
		request.setMsgType(MsgTypeEnum.news);

		EnterpriseWeChatRobotSendRequest.News news = new EnterpriseWeChatRobotSendRequest.News();
		news.setArticles(JSON.parseArray(jsonString, EnterpriseWeChatRobotSendRequest.Article.class));
		request.setNews(news);

		return send(request);
	}

	private boolean send(EnterpriseWeChatRobotSendRequest request) {
		try {
			EnterpriseWeChatRobotSendResponse response = client.execute(request);
			if (response != null && response.getErrcode() != null) {
				if (response.getErrcode() > 0) {
					log.info(response.getErrmsg());
					return false;
				}
			}
		} catch (EnterpriseWeChatApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
