package com.org.action;

import java.io.IOException;


import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller
public class CronJobScheduler
{	
	@Autowired
	private MailSender sender;
	@RequestMapping(value = "/Scheduler",method = RequestMethod.GET)
	public void sendSimpleMail(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
				
	Properties props = new Properties();
	props.put("mail.smtp.host","smtp.gmail.com");
	props.put("mail.smtp.auth", "true");
	
	Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("testfunctionality2810@gmail.com","terminate2810");
			}
			});
	
	long currentTime = System.currentTimeMillis();
	
	LocalDateTime date =
    	    LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTime), ZoneId.systemDefault());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
    String formattedTime = date.format(formatter);
    
    SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
    
    crunchifyMsg.setFrom("testfunctionality2810@gmail.com");
    crunchifyMsg.setTo("vickybala2810@gmail.com");
    crunchifyMsg.setCc("gajendran.mohan@anywhere.co");
    crunchifyMsg.setSubject("This is a test");
    crunchifyMsg.setText("Hi this is vicky!!The time now is "+formattedTime+"Enjoy the messages of cron by every 60 minutes");
    sender.send(crunchifyMsg);
    
	}
}

