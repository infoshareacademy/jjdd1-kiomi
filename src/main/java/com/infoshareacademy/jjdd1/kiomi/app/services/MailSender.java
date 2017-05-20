package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.Statistics;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticsUtility;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StringBuilderTable;

import javax.ejb.Singleton;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.math.BigInteger;
import java.util.*;
@Singleton
public class MailSender extends TimerTask {

    @Override
    public  void run() {
        System.out.println("-- Starting procedure of Sending mail ---");

        StatisticsUtility statisticsUtility = new StatisticsUtility();
        List<Statistics> listOfAllStatistics = new ArrayList<>();
        listOfAllStatistics = statisticsUtility.getAllData();

        if(listOfAllStatistics.size()>0){
            System.out.println("Data in Database in table 'Statistics'(search_history) available");

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
                message.setFrom(new InternetAddress("kiomi.info@gmail.com"));
                message.setRecipient(Message.RecipientType.TO,new InternetAddress("kiomi.info@gmail.com"));

                Map<String, BigInteger> brandAndCountMap = new LinkedHashMap<>();
                brandAndCountMap = statisticsUtility.getBrandAndCountMap();

                Map<String,BigInteger> modelAndCountMap = new LinkedHashMap<>();
                modelAndCountMap=statisticsUtility.getModelAndCountMap();

                Map<String,BigInteger> typeAndCountMap = new LinkedHashMap<>();
                typeAndCountMap=statisticsUtility.getTypeAndCountMap();

                StringBuilderTable stringBuilderTabler=new StringBuilderTable();

                String htmlTop=stringBuilderTabler.createHtmlTop();
                String htmlTableContentBrand=stringBuilderTabler.createTableContent(brandAndCountMap,"Brand");
                String htmlTableContentModel=stringBuilderTabler.createTableContent(modelAndCountMap,"Model");
                String htmlTableContentType=stringBuilderTabler.createTableContent(typeAndCountMap,"Type");
                String htmlBottom=stringBuilderTabler.createHtmlBottom();

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(htmlTop);
                stringBuilder.append(htmlTableContentBrand);
                stringBuilder.append(htmlTableContentModel);
                stringBuilder.append(htmlTableContentType);
                stringBuilder.append(htmlBottom);
                String html = stringBuilder.toString();

                Multipart multipart = new MimeMultipart( "alternative" );
                MimeBodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent( html, "text/html; charset=utf-8" );
                multipart.addBodyPart( htmlPart );

                message.setContent( multipart );

                Transport.send(message);
                System.out.println("Succces: Mail sent at: "+new Date());

            } catch (MessagingException e) {
                System.out.println("Email error: "+e);
            }
        }
        else{
            System.out.println("No data in Database in table 'Statistics'(search_history). Mail was not sent");
        }

    }
}