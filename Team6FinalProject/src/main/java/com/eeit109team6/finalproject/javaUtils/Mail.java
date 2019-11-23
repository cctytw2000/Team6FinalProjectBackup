package com.eeit109team6.finalproject.javaUtils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private String email;
	private String pwd;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean SendMessage(Integer memberId,String account , String token ) {

		final String Email = email;// your Gmail
		final String EmailPwd = pwd;// your password
		String host = "smtp.gmail.com";
		int port = 587;

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Email, EmailPwd);
			}
		});

		try {

//			String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
//					+ "/member/insertMemberInformationform?id=" + memberId + "&token=" + mem.getToken();
//			System.out.println("url = " + url);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(account));
			message.setSubject("驗證信");
			message.setText("Wellcome To FootBook \n" + "http://localhost:8080/Team6FinalProject/member/insertMemberIn"
					+ "formationform?id=" + memberId + "&token=" + token);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, Email, EmailPwd);

			Transport.send(message);


			return true ; 
		} catch (MessagingException e) {
			return false;
		}
			


	}
}
