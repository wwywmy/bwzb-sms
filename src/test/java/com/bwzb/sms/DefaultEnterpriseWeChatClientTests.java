package com.bwzb.sms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bwzb.sms.api.MsgTypeEnum;
import com.bwzb.sms.api.client.EnterpriseWeChatClient;
import com.bwzb.sms.api.request.EnterpriseWeChatRobotSendRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultEnterpriseWeChatClientTests {

	@Autowired
	private EnterpriseWeChatClient client;
	
	@Test
	public void sendTest() throws Exception {
		
		EnterpriseWeChatRobotSendRequest request = new EnterpriseWeChatRobotSendRequest();
		request.setMsgType(MsgTypeEnum.text);

		EnterpriseWeChatRobotSendRequest.Text text = new EnterpriseWeChatRobotSendRequest.Text();
		text.setContent("北京今日阵风五级风寒效应明显 周末气温略回升");

		List<String> mentionedList = new ArrayList<String>();
		mentionedList.add("王雷");
		mentionedList.add("@all");
		text.setMentionedList(mentionedList);

		List<String> mentionedMobileList = new ArrayList<String>();
		mentionedMobileList.add("13477059705");
		mentionedMobileList.add("@all");
		text.setMentionedMobileList(mentionedMobileList);

		request.setText(text);
		
		client.execute(request);
	}
}
