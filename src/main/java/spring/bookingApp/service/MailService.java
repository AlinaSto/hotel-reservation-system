package spring.bookingApp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import spring.bookingApp.model.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailService {


    private JavaMailSender emailSender;
    private UserService userService;

    @Autowired
    public MailService(JavaMailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    public void sendRateReviewMessage(
            String recepientMail, Hotel hotel) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("alinaaureliastoica@gmail.com");
        helper.setTo(recepientMail);

        helper.setSubject("Rate your stay at " + hotel.getName());
        helper.setText("Let us know how was yor stay at " + hotel.getName()+  " here: https://localhost:8080/review/add");
        emailSender.send(message);
        System.out.println("Email Message Sent Successfully");


    }
}