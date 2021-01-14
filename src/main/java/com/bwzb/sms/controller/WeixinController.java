package com.bwzb.sms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bwzb.sms.entity.CommonResult;
import com.bwzb.sms.service.IWeixinService;

@RestController
@RequestMapping("/weixin")
public class WeixinController {
	@Autowired
	private IWeixinService weixinService;

	@RequestMapping("/send")
	public Object sendText(@RequestParam("content") String content) {
		try {
			weixinService.sendText(content);
		} catch(Exception e) {
			return CommonResult.failed(e.getMessage());
		}
		
		return CommonResult.success("发送成功", null);
	}
	
	
	@RequestMapping("/sendMarkdown")
	public Object sendMarkdown(@RequestParam("content") String content) {
		try {
			weixinService.sendMarkdown(content);
		} catch(Exception e) {
			return CommonResult.failed(e.getMessage());
		}
		
		return CommonResult.success("发送成功", null);
	}
	
	@RequestMapping("/sendMarkdownMulti")
	public Object sendMarkdownMulti(@RequestParam("line") String[] line) {
		try {
			String content = StringUtils.join(line, System.getProperty("line.separator"));
			weixinService.sendMarkdown(content);
		} catch(Exception e) {
			return CommonResult.failed(e.getMessage());
		}
		
		return CommonResult.success("发送成功", null);
	}
}
