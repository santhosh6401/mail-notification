package com.mail.notification.utils;

import com.mail.notification.repository.EmailConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Service
public class Helper {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailConfigRepository configRepository;


    public String setDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = formatter.format(LocalDate.now());
        return date;

    }

    public int getRewardsNo() {

        String dateTime = LocalDateTime.now().toString();

        dateTime = dateTime.replace("-", "");
        dateTime = dateTime.replace("T", "");
        dateTime = dateTime.replace(":", "");
        dateTime = dateTime.replace(".", "");

        int dateTimeValue = 0;

        int rewardNo = 0;

        for (int i = 0; i < dateTime.length(); i++) {

            int temp = Integer.parseInt(String.valueOf(dateTime.charAt(i)));
            dateTimeValue = dateTimeValue + temp;

        }

        while (dateTimeValue > 0 || rewardNo > 9) {

            if (dateTimeValue == 0) {
                dateTimeValue = rewardNo;
                rewardNo = 0;
            }
            rewardNo += dateTimeValue % 10;
            dateTimeValue /= 10;
        }

        return rewardNo;
    }

    public long getRewardedPrice(long price, int rewardPercentage) {

        float percentageValue = (float) rewardPercentage / 100;

        return (long) (price * percentageValue);
    }

    public void sendEmail(String content, String recipient, String cc) throws MessagingException {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("msdsaran190@gmail.com");
        mailSender.setPassword("gmkfhmqsgtfjffdd");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(properties);

        String from = "msdsaran190@gmail.com";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("REMAINDER ...");
        helper.setFrom(from);
        helper.setTo(recipient);
        helper.setCc(cc);

        boolean html = true;
        helper.setText(content, html);

        mailSender.send(message);

    }

}
