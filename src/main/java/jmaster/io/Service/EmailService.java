package jmaster.io.Service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	SpringTemplateEngine templateEngine; // email template
	
	public void sendEmail(String to, String subject, String body) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,StandardCharsets.UTF_8.name());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true); // để true thì có thể show được thẻ html
			helper.setFrom("taquangdung050503@gmail.com"); // yourgmail
			
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendNewBill(String to, String name) {
		String subject = "New Bill " + name;
		
		Context ctx = new Context();
		ctx.setVariable("name", name);
		
		String body = templateEngine.process("email/new-bill.html", ctx);
		sendEmail(to, subject, body);
	}
	
	public void sendNewPassword(String to, String body) {
		String subject = "Password new";
		sendEmail(to, subject, body);
	}
}
