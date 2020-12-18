package com.bwzb.sms.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.bwzb.sms.entity.weixin.NewArticle;

public interface IWeixinService {
	boolean sendText(String s);

	boolean sendText(String s, List<String> mentionedList);

	boolean sendText(String s, List<String> mentionedList, List<String> mentionedMobileList);

	boolean sendMarkdown(String content);

	boolean sendImage(byte[] source);

	boolean sendImage(File file);
	
	boolean sendImage(InputStream in);

	boolean sendNews(List<NewArticle> newArticleList);
}
