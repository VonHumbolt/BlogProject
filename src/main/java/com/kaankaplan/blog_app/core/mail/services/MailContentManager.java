package com.kaankaplan.blog_app.core.mail.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentManager {

    private final TemplateEngine templateEngine;

    public String buildTemplateMessage(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailContent", context);
    }
}
