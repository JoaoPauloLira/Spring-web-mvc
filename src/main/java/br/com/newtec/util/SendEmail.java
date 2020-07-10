package br.com.newtec.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	@Autowired
	private MailSender sender;

	public boolean enviarEmail(String assunto, String enviarParaQuem, String mensagem) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject(assunto);
		mailMessage.setTo(enviarParaQuem);
		mailMessage.setText(mensagem);
		mailMessage.setFrom("newtec.testes.jp@gmail.com");

		sender.send(mailMessage);
		return true;
	}

}
