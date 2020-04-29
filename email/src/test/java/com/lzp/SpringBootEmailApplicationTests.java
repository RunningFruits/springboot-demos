package com.lzp;

import com.lzp.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEmailApplicationTests {

    @Value("${spring.mail.username}")
    private String userName;

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userName);//发送者
        message.setTo("brightereyer2@163.com");//接收者
        message.setSubject("测试主题");//邮件主题
        message.setText("测试内容");//邮件内容
        javaMailSender.send(message);
    }


    @Resource
    MailService mailService;

    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail(
                "brightereyer2@163.com",
                "这是一封简单文本邮件",
                "明天很美好，今天很重要"
        );
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello 明天要加油哦！</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("brightereyer2@163.com", "这是一封html邮件", content);
    }

}
