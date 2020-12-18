package com.bwzb.sms.controller;

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
}
