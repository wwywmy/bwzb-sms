package com.bwzb.sms.service.impl.monyun;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwzb.sms.exception.SmsException;
import com.bwzb.sms.service.ISmsService;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsServiceMonyunImpl implements ISmsService {

	@Value("${sms.monyun.url}")
	private String url;
	@Value("${sms.monyun.userid}")
	private String userid;
	@Value("${sms.monyun.pwd}")
	private String pwd;

	@Override
	public boolean send(String mobile, String content) {
		String timestamp = FastDateFormat.getInstance("MMddHHmmss").format(new DateTime());// 时间戳：采用24小时制格式MMDDHHMMSS

		Map<String, String> mapParams = new HashMap<String, String>();
		mapParams.put("userid", userid);
		mapParams.put("pwd", SmsMonyunUtil.authentication(userid, pwd, timestamp));
		mapParams.put("mobile", mobile);
		try {
			mapParams.put("content", URLEncoder.encode(content, "GBK"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
			throw new SmsException(e.getMessage());
		}
		mapParams.put("timestamp", timestamp);

		try {
			String requestJsonString = JSON.toJSONString(mapParams);
			log.info("url={}",url);
			log.info("requestJsonString={}",requestJsonString);
			String responseJsonString = HttpUtil.post(url, requestJsonString);
			log.info("responseJsonString={}",responseJsonString);
			if (StringUtils.isNotBlank(responseJsonString)) {
				JSONObject jsonObject = JSONObject.parseObject(responseJsonString);
				if (jsonObject.containsKey("result")) {
					int result = jsonObject.getInteger("result");
					if (0 == result) {// 短信发送请求处理结果：0代表成功；非0代表失败。错误代码详见附录
//						log.info("response success");
//						long msgid = jsonObject.getLong("msgid");
//						String custid = jsonObject.getString("custid");
//
//						log.info("msgid={}", String.valueOf(msgid));
//						log.info("custid={}", custid);

						return true;

					} else {
						log.error("response error");
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SmsException(e.getMessage());
		}

		return false;
	}

}
