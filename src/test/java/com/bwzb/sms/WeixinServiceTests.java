package com.bwzb.sms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bwzb.sms.api.MarkdownColorEnum;
import com.bwzb.sms.api.request.EnterpriseWeChatRobotSendRequest;
import com.bwzb.sms.entity.weixin.NewArticle;
import com.bwzb.sms.service.IWeixinService;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeixinServiceTests {

	@Autowired
	private IWeixinService weixinService;

	@Test
	public void sendTextTest() throws Exception {
		weixinService.sendText("Hello world Jenkins notice");

	}

	@Test
	public void sendMarkdownTest() throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("msgtype", "markdown");
//
//		Map<String, Object> markdown = new HashMap<String, Object>();
//		markdown.put("content", "实时新增用户反馈<font color=\"warning\">132例</font>，请相关同事注意。\n"
//		     +  "  >类型:<font color=\"comment\">用户反馈</font> \n"
//		     +  "  >普通用户反馈:<font color=\"comment\">117例</font> \n"
//		     +  "  >VIP用户反馈:<font color=\"comment\">15例</font>");
//
//		map.put("markdown", markdown);
//
//		String jsonText = JSONUtil.toJsonStr(map);
//		log.info(jsonText);
//		weixinService.sendText(jsonText);

		EnterpriseWeChatRobotSendRequest.Markdown markdown = new EnterpriseWeChatRobotSendRequest.Markdown();
		markdown.appendTitle("一级标题", 1);
		markdown.appendTitle("level 3", 3);
		markdown.appendBold("请点击如下链接");
		markdown.appendLink("百度", "https://www.baidu.com");
		markdown.appendColorText("信息", MarkdownColorEnum.info);
		markdown.appendColorText("注释", MarkdownColorEnum.comment);
		markdown.appendColorText("警告", MarkdownColorEnum.warning);
		markdown.append("What was that marvellous quote that she came out with?");
		markdown.append("\n");
		markdown.appendQuote("adduction 引用，内收");
		markdown.appendQuote("recommend 推荐; 介绍; 劝告，建议; 使受欢迎;");

		weixinService.sendMarkdown(markdown.getContent());

	}

	@Test
	public void sendImageTest() throws Exception {
//		File file = new File("d:/m1.png");
//		String base64 = Base64.encode(file);
//		
//		String md5 = MD5.create().digestHex(file);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("msgtype", "image");
//
//		Map<String, Object> image = new HashMap<String, Object>();
//		image.put("base64", base64);
//		image.put("md5", md5);
//
//		map.put("image", image);
//
//		String jsonText = JSONUtil.toJsonStr(map);
//		log.info(jsonText);
//		weixinService.sendText(jsonText);

		File file = new File("d:/m4.png");
		byte[] buf = FileUtil.readBytes(file);
		weixinService.sendImage(buf);
	}

	@Test
	public void sendNewsTest() throws Exception {

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("msgtype", "news");
//
//		Map<String, Object> news = new HashMap<String, Object>();
//		
//		List<Map<String,Object>> articles = new ArrayList<Map<String,Object>>();
//		
//		
//		Map<String, Object> articleA = new HashMap<String, Object>();
//		articleA.put("title", "中秋节礼品领取A");
//		articleA.put("description", "今年中秋节公司有豪礼相送");
//		articleA.put("url", "http://www.baidu.com");
//		articleA.put("picurl", "https://bwzb-recruit-prod.oss-cn-shenzhen.aliyuncs.com/image/m1.png?Expires=1608286603&OSSAccessKeyId=TMP.3KiT5hZ3kU6PApPArAjUBhkwLRmyZLXoegxed23QZFN3ZVxvwGiFksANvAh8YpeEgbJM63uwAscrGDCFesJ4FDxgBUaFNr&Signature=4PVrFa%2Bfr5zCaQhBhUKWopwGKjY%3D&versionId=CAEQHxiBgID59cXUsxciIDg2YjhkMjUxMDBiNzQ2MzJhZWJmNWVhNWZkNGU2MDVm&response-content-type=application%2Foctet-stream");
//		articles.add(articleA);
//		
////		Map<String, Object> articleB = new HashMap<String, Object>();
////		articleB.put("title", "中秋节礼品领取B");
////		articleB.put("description", "今年中秋节公司有豪礼相送");
////		articleB.put("url", "http://www.sina.com.cn");
////		articleB.put("picurl", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2634306427,1511025022&fm=26&gp=0.jpg");
////		articles.add(articleB);
////		
////		Map<String, Object> articleC = new HashMap<String, Object>();
////		articleC.put("title", "中秋节礼品领取C");
////		articleC.put("description", "今年中秋节公司有豪礼相送");
////		articleC.put("url", "http://www.hao123.com");
////		articleC.put("picurl", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607594724023&di=620f87407c2aab150c3107d8c6aea06d&imgtype=0&src=http%3A%2F%2Fimglf3.nosdn.127.net%2Fimg%2FL0lHRjFqbkVQa2pIQXFwYWE2WUd6Q3A4dUN5aFJhZlBOaTd0b3VDVFlQNUNTeGpQUGpZbGF3PT0.jpg%3FimageView%26thumbnail%3D1680x0%26quality%3D96%26stripmeta%3D0%26type%3Djpg%257Cwatermark%26type%3D2%26text%3Dwqkg");
////		articles.add(articleC);
//
//	    news.put("articles", articles);
//		map.put("news", news);
//
//		String jsonText = JSONUtil.toJsonStr(map);
//		log.info(jsonText);
//		weixinService.sendText(jsonText);

		NewArticle articleA = new NewArticle();
		articleA.setTitle("中秋节礼品领取A");
		articleA.setDescription("今年中秋节公司有豪礼相送");
		articleA.setUrl("http://www.baidu.com");
		articleA.setPicurl("https://bwzb-pub-file.oss-cn-shenzhen.aliyuncs.com/m3.png");
		
		NewArticle articleB = new NewArticle();
		articleB.setTitle("中秋节礼品领取1124");
		articleB.setDescription("今年中秋节公司有豪礼相送");
		articleB.setUrl("http://www.baidu.com");
		articleB.setPicurl("https://bwzb-pub-file.oss-cn-shenzhen.aliyuncs.com/m4.png");

		List<NewArticle> list = new ArrayList<NewArticle>();
		list.add(articleB);
		list.add(articleA);
		
		weixinService.sendNews(list);

	}
}
