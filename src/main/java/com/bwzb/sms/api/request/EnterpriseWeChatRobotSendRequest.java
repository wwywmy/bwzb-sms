package com.bwzb.sms.api.request;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.bwzb.sms.api.EnterpriseWeChatObject;
import com.bwzb.sms.api.MarkdownColorEnum;
import com.bwzb.sms.api.MsgTypeEnum;
import com.bwzb.sms.api.response.EnterpriseWeChatRobotSendResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EnterpriseWeChatRobotSendRequest extends BaseEnterpriseWeChatRequest<EnterpriseWeChatRobotSendResponse> {

	@JSONField(name = "msgtype")
	private MsgTypeEnum msgType;

	@JSONField(name = "text")
	private Text text;

	@JSONField(name = "markdown")
	private Markdown markdown;

	@JSONField(name = "image")
	private Image image;

	@JSONField(name = "news")
	private News news;

	@Override
	public String getApiMethodName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<EnterpriseWeChatRobotSendResponse> getResponseClass() {
		return EnterpriseWeChatRobotSendResponse.class;
	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class Text extends EnterpriseWeChatObject {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2010983797863499500L;

		@JSONField(name = "content")
		private String content;
		@JSONField(name = "mentioned_list")
		private List<String> mentionedList;
		@JSONField(name = "mentioned_mobile_list")
		private List<String> mentionedMobileList;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class Markdown extends EnterpriseWeChatObject {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2783034232337796067L;

		@JSONField(name = "content")
		private String content = "";

		public void append(String s) {
			content = StringUtils.join(//
					content, //
					s//
			);
		}

		public void appendTitle(String s, int level) {
			// 支持1至6级标题
			if (level < 1) {
				level = 1;
			} else if (level > 6) {
				level = 6;
			}
			content = StringUtils.join(//
					content, //
					System.getProperty("line.separator"), //
					StringUtils.repeat("#", level), //
					" ", //
					s, //
					System.getProperty("line.separator"));
		}

		public void appendBold(String s) {
			content = StringUtils.join(//
					content, //
					"**", //
					s, //
					"**" //
			);
		}

		public void appendLink(String s, String url) {
			content = StringUtils.join(//
					content, //
					"[", //
					s, //
					"]", //
					"(", //
					url, //
					")" //
			);
		}

		public void appendCode(String s) {
			content = StringUtils.join(//
					content, //
					"`", //
					s, //
					"`" //
			);
		}

		public void appendQuote(String s) {
			content = StringUtils.join(//
					content, //
					">", //
					" ", //
					s, //
					System.getProperty("line.separator"));
		}

		public void appendColorText(String s, MarkdownColorEnum color) {
			content = StringUtils.join(//
					content, //
					"<font color=\"" + color + "\">", //
					s, //
					"</font>");
		}

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class Image extends EnterpriseWeChatObject {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3185941846530083160L;

		@JSONField(name = "base64")
		private String base64;

		@JSONField(name = "md5")
		private String md5;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class News extends EnterpriseWeChatObject {

		/**
		 * 
		 */
		private static final long serialVersionUID = 212563737525267221L;

		@JSONField(name = "articles")
		private List<Article> articles;

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class Article extends EnterpriseWeChatObject {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6663937640721785024L;

		@JSONField(name = "title")
		private String title;
		@JSONField(name = "description")
		private String description;
		@JSONField(name = "url")
		private String url;
		@JSONField(name = "picurl")
		private String picurl;

	}

//	public static void main(String[] args) {
//		EnterpriseWeChatRobotSendRequest request = new EnterpriseWeChatRobotSendRequest();
//		request.setMsgType("text");
//
//		EnterpriseWeChatRobotSendRequest.Text text = new EnterpriseWeChatRobotSendRequest.Text();
//		text.setContent("广州今日天气：29度，大部分多云，降雨概率：60%");
//
//		List<String> mentionedList = new ArrayList<String>();
//		mentionedList.add("wangqing");
//		mentionedList.add("@all");
//		text.setMentionedList(mentionedList);
//
//		List<String> mentionedMobileList = new ArrayList<String>();
//		mentionedMobileList.add("13800001111");
//		mentionedMobileList.add("@all");
//		text.setMentionedMobileList(mentionedMobileList);
//
//		request.setText(text);
//
//		String jsonOutput = JSON.toJSONString(request);
//		System.out.println(jsonOutput);
//	}
}
