package com.bwzb.sms.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import cn.hutool.core.date.DateUtil;

@RestController
@RequestMapping("/hello")
public class HelloController {
	
	@Value("${spring.mail.username}")
	private String username;
	
	@RequestMapping("/say")
	public Object say() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("name", new Faker(Locale.SIMPLIFIED_CHINESE).name().fullName());
		map.put("time", DateUtil.today());
		return map;
	}
}
