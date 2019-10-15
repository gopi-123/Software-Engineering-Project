package models;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;
import com.feth.play.module.pa.user.*;
import helper.datasources.MorphiaObject;
import models.TokenAction.Type;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.*;


public class HirePeople {

		public HirePeople() {}

                public String fullName;

                public String department;

                public String empID;

                public String category;

                public String eMail;

		public void sendRequestToAdmin() {

                        final String username = "hrtoolinterne@gmail.com";
                        final String password = "hrtool123";

                        Properties props = new Properties();
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.port", "587");

                        try {
                                Session session = Session.getInstance(props,
                                        new javax.mail.Authenticator() {
                                                protected PasswordAuthentication getPasswordAuthentication() {
                                                return new PasswordAuthentication(username, password);
                                        }
                                });

                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress("hrtoolinterne@gmail.com"));
                                message.setRecipients(Message.RecipientType.TO,
                                        InternetAddress.parse("hrtoolinterne@gmail.com"));
                                message.setSubject(this.fullName + " Beantragter Zugang für " + this.category);
                                message.setText(this.fullName + " Interessiert sich für die Einstellung von Personen unter Kategorie " + this.category + "\nBitte geben Sie ihm den Zugang zu\nSeine Information ist:\n\tVollständiger Name:\t" + this.fullName + "\n\tFakultät:\t" + this.department + "\n\tMitarbeiter ID:\t" + this.empID + "\n\te-Mail ID:\t" + this.eMail);

                                Transport.send(message);

                                System.out.println("Done");

                        } catch (MessagingException e) {
                                throw new RuntimeException(e);
                        }
		}
}
