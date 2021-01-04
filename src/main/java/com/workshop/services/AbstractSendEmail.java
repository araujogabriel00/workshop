package com.workshop.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.workshop.entitites.Cliente;
import com.workshop.entitites.Pedido;

public abstract class AbstractSendEmail implements EmailService {

	@Value("${default.sender}")
	private String sender;

	/*
	 * @Autowired private JavaMailSender javaMailSender;
	 */

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);

	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! " + "Código: " + pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		return sm;
	}

	/*
	 * protected String htmlFromTemplatePedido(Pedido obj) { Context context = new
	 * Context(); context.setVariable("pedido", obj); return
	 * templateEngine.process("email/confirmacaoPedido", context);
	 * 
	 * }
	 * 
	 * /*public void sendOrderConfirmationHtmlEmail(Pedido obj) { MimeMessage mm;
	 * try { mm = prepareMimeMessageFromPedido(obj); sendHtmlEmail(mm); } catch
	 * (MessagingException e) { sendOrderConfirmationEmail(obj); }
	 * 
	 * }
	 */

	/*
	 * protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws
	 * MessagingException { MimeMessage mimeMessage =
	 * javaMailSender.createMimeMessage(); MimeMessageHelper mmh = new
	 * MimeMessageHelper(mimeMessage, true); mmh.setTo(obj.getCliente().getEmail());
	 * mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
	 * mmh.setSentDate(new Date(System.currentTimeMillis()));
	 * mmh.setText(htmlFromTemplatePedido(obj), true);
	 * 
	 * return mimeMessage; }
	 */

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		return sm;

	}

}
