package edu.tamu.scholars.middleware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.config.MailConfig;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private MailConfig mailConfig;

    public void send(String to, String subject, String message) {
        send(to, subject, message, mailConfig.getFrom(), mailConfig.getReplyTo());
    }

    private void send(String to, String subject, String message, String from, String replyTo) {
        emailSender.send(createMimeMessagePreparator(to, subject, message, from, replyTo));
    }

    MimeMessagePreparator createMimeMessagePreparator(String to, String subject, String message, String from, String replyTo) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setReplyTo(replyTo);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
        };
    }

}
