package com.bwzb.sms.service;

public interface IMailService {
	boolean send(String mail, String title, String content);
}
