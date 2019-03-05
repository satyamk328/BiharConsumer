package com.db.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
/**
 * @author Satyam Kumar
 *
 */
@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class MailConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mail.host"));
		mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
		mailSender.setUsername(env.getProperty("mail.username"));
		mailSender.setPassword(env.getProperty("mail.password"));
		mailSender.setJavaMailProperties(mailProperties());
		return mailSender;
	}

	public Properties mailProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		properties.put("mail.smtp.starttls.required", env.getProperty("mail.smtp.starttls.required"));
		properties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		properties.put("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
		properties.put("mail.debug", env.getProperty("mail.debug"));
		return properties;
	}

}
