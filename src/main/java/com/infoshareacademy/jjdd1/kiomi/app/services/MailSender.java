package com.infoshareacademy.jjdd1.kiomi.app.services;

import javax.ejb.Singleton;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class MailSender extends TimerTask {

    @Override
    public  void run() {
        System.out.println("-- Starting procedure of Sending mail ---");

        Properties properties=new Properties();
        properties.setProperty("mail.host","smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port","587");
        properties.setProperty("mail.smtp.auth","true");

        Authenticator authenticator=new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kiomi.info@gmail.com","kiomi2017");
            }
        };

        Session session=Session.getDefaultInstance(properties,authenticator);

        Message message=new MimeMessage(session);

        try {
            message.setSubject("Message From 'Kiomi Autoparts'");
            message.setText("Info concerning statistics of autoparts");
            message.setFrom(new InternetAddress("kiomi.info@gmail.com"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress("kiomi.info@gmail.com"));
            Transport.send(message);
            System.out.println("Succces: Mail sent at: "+new Date());

        } catch (MessagingException e) {
            System.out.println("Email error: "+e);
        }
        finally {
            System.out.println("==============================================");
        }

    }
}
