package com.bwzb.sms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bwzb.sms.service.IDingdingService;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DingdingServiceTests {

	@Autowired
	private IDingdingService dingdingService;

	@Test
	public void sendTextTest() throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("msgtype", "text");
//
//		Map<String, Object> text = new HashMap<String, Object>();
//		text.put("content", "Hello world Dingding notice");
//
//		map.put("text", text);

		String jsonText = "DingTalk测试消息" //
				+ RandomUtil.randomString(10)//
				+ "-" //
				+ RandomUtil.randomInt(1000000,10000000)//
		;
		log.info(jsonText);
		dingdingService.sendText(jsonText);

	}
	
	
	
}
