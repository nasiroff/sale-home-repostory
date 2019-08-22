package com.step.salehome.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:message.properties")
public class EmailUtil {

    @Autowired
    public JavaMailSender emailSender;

    @Value("${email.message.template}")
    private String messageTemplate;

    public void sendSimpleMessage(String to, String name, String token) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Activate Message");
        message.setText(String.format(messageTemplate, name, token));
        emailSender.send(message);

    }


    public void sendMessageToPostOwner(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


}
