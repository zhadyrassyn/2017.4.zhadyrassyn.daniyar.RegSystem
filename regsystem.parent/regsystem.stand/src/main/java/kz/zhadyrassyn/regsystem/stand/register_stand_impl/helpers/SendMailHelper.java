package kz.zhadyrassyn.regsystem.stand.register_stand_impl.helpers;

import kz.zhadyrassyn.regsystem.stand.register_stand_impl.constants.CommonConstants;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailHelper {

    public String to;
    public String text;

    public SendMailHelper(String to, String text) {
        this.to = to;
        this.text = text;
    }

    public void send() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(CommonConstants.email,
                                CommonConstants.emailPass);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(CommonConstants.email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Registration");

            message.setText("Dear user. We are glad to welcome you!\n\n"
                    + "Please, follow to link below to confirm your registration\n\n"
                    + "http://localhost:1234/regsystem/api/sign/up/" + text
            );

            Transport.send(message);

            System.out.println("Message has sent!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
