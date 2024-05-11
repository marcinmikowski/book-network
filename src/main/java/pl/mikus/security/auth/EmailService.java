package pl.mikus.security.auth;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String userName,
            EmailTemplate emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException {
        String templateName = Objects.isNull(emailTemplate) ? "confirm-email" : emailTemplate.getName();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> emailProperties = new HashMap<>();
        emailProperties.put("userName", userName);
        emailProperties.put("confirmationUrl", confirmationUrl);
        emailProperties.put("activationCode", activationCode);

        Context templateContext = new Context();
        templateContext.setVariables(emailProperties);

        mimeMessageHelper.setFrom("m.mikowski@wp.pl");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        String emailContent = templateEngine.process(templateName, templateContext);
        mimeMessageHelper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

}
