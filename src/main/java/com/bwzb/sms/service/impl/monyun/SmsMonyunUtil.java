package com.bwzb.sms.service.impl.monyun;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

public class SmsMonyunUtil {
	public static String authentication(String userid, String pwd, String timestamp) {
		String text = StringUtils.join(userid.toUpperCase(), "00000000", pwd, timestamp);
		String md5 = DigestUtils.md5DigestAsHex(text.getBytes());

		return md5;
	}
}
