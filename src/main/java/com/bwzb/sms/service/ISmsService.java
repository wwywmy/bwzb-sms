package com.bwzb.sms.service;

public interface ISmsService {
	boolean send(String email, String content);
}
