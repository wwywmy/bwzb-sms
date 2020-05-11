package com.bwzb.sms.service.impl;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bwzb.sms.service.IMailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements IMailService {
	@Resource
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String emailUserName;

	@Value("${system.name}")
	private String systemName;

	@Override
	public boolean send(String mail, String title, String content) {
		boolean success = false;
		// make mail
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailUserName, systemName);
			helper.setTo(mail);
			helper.setSubject(title);
			helper.setText(content, true);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			log.error("send mail error", e);

		}

		success = true;
		return success;
	}

}
