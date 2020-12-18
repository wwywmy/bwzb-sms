package com.bwzb.sms;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.bwzb.sms.api.MsgTypeEnum;

@SpringBootTest
class BwzbSmsApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(MsgTypeEnum.text);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msgtype", MsgTypeEnum.image);
		
		System.out.println(JSON.toJSONString(map));
	}

}
