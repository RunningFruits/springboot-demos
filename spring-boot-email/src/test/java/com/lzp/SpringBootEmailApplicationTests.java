package com.lzp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEmailApplicationTests {

	@Value("${spring.mail.username}")
	private String userName;

	@Autowired
	private JavaMailSender javaMailSender;

	@Test
	public void sendSimpleEmail(){
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(userName);//发送者
		message.setTo("2017720063@qq.com");//接收者
		message.setSubject("测试主题");//邮件主题
		message.setText("测试内容");//邮件内容
		javaMailSender.send(message);
	}
}
