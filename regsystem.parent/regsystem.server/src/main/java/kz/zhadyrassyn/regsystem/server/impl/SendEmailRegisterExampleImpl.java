package kz.zhadyrassyn.regsystem.server.impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;
import kz.zhadyrassyn.regsystem.controller.register.SendEmailRegister;
import org.apache.log4j.Logger;

@Bean
public class SendEmailRegisterExampleImpl implements SendEmailRegister {

    public BeanGetter<EmailSender> emailSender;

    public BeanGetter<EmailSenderController> emailSenderController;

    private static final Logger LOG = Logger.getLogger(SendEmailRegister.class);



    @Override
    public void toSend() {
        emailSenderController.get().sendAllExistingEmails();
    }

    @Override
    public void prepareSendEmail() {
        Email email = new Email();
        email.setFrom("dandibobo537@gmail.com");
        email.setTo("dandibobo537@gmail.com");
        email.setSubject("Test server Email");
        email.setBody("Message text");
        emailSender.get().send(email);
    }
}