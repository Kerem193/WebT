package controllers;

import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.*;
import play.mvc.BodyParser;                     
import play.libs.Json;
import play.libs.Json.*;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Kontakt extends Controller {
	
	//@Inject MailerClient mailerClient;

	public Result kontaktNotLogged() {
		
		JsonNode kontakt = request().body().asJson();
		
		ObjectNode result = Json.newObject();
		
		String email = kontakt.get("email").asText();
		String betreff = kontakt.get("betreff").asText();
		String nachricht = kontakt.get("nachricht").asText();
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("","");
				}
			});
		
		boolean ergebnis;
				
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("keuyanik@htwg-konstanz.de"));
			message.setSubject(betreff);
			message.setText(nachricht + "\n\nDiese Nachricht ist von: " + email);

			Transport.send(message);

			System.out.println("Done");
			
			ergebnis = true;

		} catch (MessagingException e) {
			
			ergebnis = false;
			
			throw new RuntimeException(e);
			
		} catch (RuntimeException e){
			
			System.out.println("Fehler");
			
			ergebnis = false;
		}
		
		if(ergebnis == true) {
			
			String erfolg = "Nachricht erfolgreich verschickt.";
			
			result.put("status", "ok");
			result.put("message", erfolg);
			
			System.out.println(result);
			
			return ok(result);
			
		} else {
			
			String fehler = "Nachricht konnte nicht verschickt werden.";
			
			result.put("status", "error");
			result.put("message", fehler);

			return ok(result);
			
		}
	}
	
}
