package com.kaankaplan.blog_app.core.mail.services;

import com.kaankaplan.blog_app.core.mail.abstracts.MailService;
import com.kaankaplan.blog_app.core.mail.modal.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSenderManager implements MailService {

    private final JavaMailSender javaMailSender;
    private final MailContentManager mailContentManager;

    @Async
    @Override
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("testmail@example.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentManager.buildTemplateMessage(notificationEmail.getBody()));
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            throw new RuntimeException("An exception occured when sending email");
        }
    }
}
