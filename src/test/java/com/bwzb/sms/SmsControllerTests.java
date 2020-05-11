package com.bwzb.sms;

import java.nio.charset.Charset;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SmsControllerTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sendTest() throws Exception {
		
		Faker faker = new Faker(Locale.SIMPLIFIED_CHINESE);
		String content = StringUtils.join("姓名：",faker.name().fullName(),",证件号：",RandomUtil.randomNumbers(8));

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("mobile", "13477059705");
		params.add("content", content);

		String responseString = mockMvc.perform(//
				MockMvcRequestBuilders//
						.get("/sms/send")//
						.params(params)//
		)//
				.andExpect(MockMvcResultMatchers.status().isOk()) // 返回的状态是200
				.andReturn()//
				.getResponse()//
				.getContentAsString(Charset.forName("UTF-8"))//
		;
		log.info(responseString);
	}
}
