package com.digital.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * @author Satyam Kumar
 *
 */
@Component
public class MailService {

	private static final Logger log = LoggerFactory.getLogger(MailService.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${isOffline}")
	private boolean isOffLine;

	public void sendEmail(String Subject, String emailTo, String Content) {
		log.info("call sendEmail()");
		if (isOffLine)
			return;
		sendMail(emailTo, null, null, Subject, Content);
	}

	public void sendMail(final String emailTo, final String emailCC, final String emailBcc, final String Subject,
			final String Content) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				if (emailCC != null && !"".equals(emailCC.trim())) {
					helper.setCc(InternetAddress.parse(emailCC));
				}
				if (emailBcc != null && !"".equals(emailBcc.trim())) {
					helper.setBcc(InternetAddress.parse(emailBcc));
				}
				helper.setSubject(Subject);
				helper.setTo(InternetAddress.parse(emailTo));
				helper.setText(Content, true);
			}
		};
		mailSender.send(preparator);
		log.info("Sent Mail Message to : {}", emailTo);
	}
}
