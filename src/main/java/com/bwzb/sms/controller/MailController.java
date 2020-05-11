package com.bwzb.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bwzb.sms.entity.CommonResult;
import com.bwzb.sms.exception.MailException;
import com.bwzb.sms.service.IMailService;

@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
	private IMailService mailService;

	@RequestMapping("/send")
	public Object send(@RequestParam("mail") String mail, @RequestParam("title") String title,
			@RequestParam("content") String content) {
		try {
			mailService.send(mail, title, content);
		} catch (MailException e) {
			return CommonResult.failed(e.getMessage());
		}

		return CommonResult.success("发送成功", null);
	}
}
