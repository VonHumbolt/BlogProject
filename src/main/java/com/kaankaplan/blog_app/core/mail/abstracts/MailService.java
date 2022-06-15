package com.kaankaplan.blog_app.core.mail.abstracts;

import com.kaankaplan.blog_app.core.mail.modal.NotificationEmail;

public interface MailService {

    void sendMail(NotificationEmail notificationEmail);
}
