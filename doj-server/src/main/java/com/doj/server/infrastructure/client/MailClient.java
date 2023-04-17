package com.doj.server.infrastructure.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 类描述: 邮件客户端
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
@Slf4j
@Component
public class MailClient {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String target, String subject, String content){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            // 设置邮件内容：
            // 1. 发件人
            // 2. 收件人
            // 3. title
            // 4. 内容
            helper.setFrom(from);
            helper.setTo(target);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(helper.getMimeMessage());
        }catch (MessagingException e){
            log.error("发送邮件失败： " + e.getMessage());
        }
    }
}
