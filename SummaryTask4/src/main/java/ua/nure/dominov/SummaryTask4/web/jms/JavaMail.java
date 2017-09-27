package ua.nure.dominov.SummaryTask4.web.jms;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.VerificationCode;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;

/**
 * @author calango
 *
 */
public final class JavaMail {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			JavaMail.class.getName());
	
	/**
	 * 
	 */
	private JavaMail() {
		super();
	}

	/**
	 * @param regForm
	 * @param verForm
	 */
	public static void confirmMail(final User regForm,
			final VerificationCode verForm) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(
        		FilesPathsStorage.EMAILPATH);
		final Properties props = new Properties();
		props.put(resourceBundle.getString("auth"), resourceBundle.getString("authstate"));
		props.put(resourceBundle.getString("enable"), resourceBundle.getString("enablestate"));
		props.put(resourceBundle.getString("host"), resourceBundle.getString("hostname"));
		props.put(resourceBundle.getString("port"), resourceBundle.getString("portnumber"));
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(resourceBundle.getString("username"),
						resourceBundle.getString("password"));
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(resourceBundle.getString("username")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(regForm.getEmail()));
			message.setSubject("Confirmation of registration");
			message.setText("Dear Mr/Ms " + regForm.getName()
				+ ",\n\n To confirm your mail address you should to move "
				+ "by this link:\n" 
				+ "http://localhost:8080/SummaryTask4/pages/confirm?code=" 
				+ verForm.getVerificationUserCode());
			Transport.send(message);
		} catch (MessagingException e) {
			LOGGER.error(e);
		}
	}
	
	/**
	 * @param user
	 * @param email
	 * @param subject
	 * @param text
	 */
	public static void sendMail(User user, final String email, 
			final String subject, final String text) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(
        		FilesPathsStorage.EMAILPATH);
		Properties props = new Properties();
		props.put(resourceBundle.getString("auth"), resourceBundle.getString("authstate"));
		props.put(resourceBundle.getString("enable"), resourceBundle.getString("enablestate"));
		props.put(resourceBundle.getString("host"), resourceBundle.getString("hostname"));
		props.put(resourceBundle.getString("port"), resourceBundle.getString("portnumber"));
 
		if (user == null) {
			user = new User();
		}
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(resourceBundle.getString("username"),
						resourceBundle.getString("password"));
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(resourceBundle.getString("username")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(resourceBundle.getString("username")));
			message.setSubject(subject);
			message.setText(user.toString() + "\n" + text);
			Transport.send(message);
		} catch (MessagingException e) {
			LOGGER.error(e);
		}
	}
	
	/**
	 * @param email
	 */
	public static void mailOfSuccess(final String email) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(
        		FilesPathsStorage.EMAILPATH);
		Properties props = new Properties();
		props.put(resourceBundle.getString("auth"), resourceBundle.getString("authstate"));
		props.put(resourceBundle.getString("enable"), resourceBundle.getString("enablestate"));
		props.put(resourceBundle.getString("host"), resourceBundle.getString("hostname"));
		props.put(resourceBundle.getString("port"), resourceBundle.getString("portnumber"));
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(resourceBundle.getString("username"),
						resourceBundle.getString("password"));
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(resourceBundle.getString("username")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Confirmation of registration");
			message.setText("Your account was successfully confirmed!");
			Transport.send(message);
		} catch (MessagingException e) {
			LOGGER.error(e);
		}
	}
	
	/**
	 * @param tourName
	 * @param user
	 * @param status
	 * @param price
	 */
	public static void tourReservationStatus(final String tourName, 
			final User user, final String status, final long price) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(
        		FilesPathsStorage.EMAILPATH);
		Properties props = new Properties();
		props.put(resourceBundle.getString("auth"), resourceBundle.getString("authstate"));
		props.put(resourceBundle.getString("enable"), resourceBundle.getString("enablestate"));
		props.put(resourceBundle.getString("host"), resourceBundle.getString("hostname"));
		props.put(resourceBundle.getString("port"), resourceBundle.getString("portnumber"));
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(resourceBundle.getString("username"),
						resourceBundle.getString("password"));
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(resourceBundle.getString("username")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user.getEmail()));
			message.setSubject("Tour staus was changed.");
			message.setText("Dear Mr/Ms " + user.getName() + "\n" 
					+ "Your tour " + tourName + " now have status \"" + status
					+ "\".\n Total price with all your discounts is " + price);
			Transport.send(message);
			LOGGER.info("The tour status letter was successfully sended!");
		} catch (MessagingException e) {
			LOGGER.error(e);
		}
	}
	
}
