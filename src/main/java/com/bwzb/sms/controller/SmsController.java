package com.bwzb.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bwzb.sms.entity.CommonResult;
import com.bwzb.sms.exception.SmsException;
import com.bwzb.sms.service.ISmsService;

@RestController
@RequestMapping("/sms")
public class SmsController {
	@Autowired
	private ISmsService smsService;

	@RequestMapping("/send")
	public Object send(@RequestParam("mobile") String mobile, @RequestParam("content") String content) {
		try {
			smsService.send(mobile, content);
		} catch(SmsException e) {
			return CommonResult.failed(e.getMessage());
		}
		
		return CommonResult.success("发送成功", null);
	}
}
