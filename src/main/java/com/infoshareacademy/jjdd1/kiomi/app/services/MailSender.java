package com.infoshareacademy.jjdd1.kiomi.app.services;


import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public static void main(String[] args) throws MessagingException {

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

        message.setSubject("Message From 'Kiomi Autoparts'");
        message.setText("Info concerning statistics of autoparts");
//        message.setFrom(new InternetAddress("123@wp.pl"));

        message.setRecipient(Message.RecipientType.TO,new InternetAddress("kiomi.info@gmail.com"));
        Transport.send(message);
        System.out.println("Succces: Mail sent!");
        System.out.println("==============================================");
    }
}
