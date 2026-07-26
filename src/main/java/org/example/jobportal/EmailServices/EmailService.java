package org.example.jobportal.EmailServices;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender jsm;

    public EmailService(JavaMailSender jsm) {
        this.jsm = jsm;
    }
    public void sendApplicationReceived(String to) throws MessagingException {
        MimeMessage message = jsm.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Application Received");
        String body="""
                <html>
                <body>
                    <h2>Application Received</h2>
                    <p>Dear Candidate,</p>
                    <p>Your application has been successfully received. Our team will review it shortly.</p>
                    <p>Regards,<br>HR Team</p>
                </body>
                </html>
                """;
        helper.setText(body,true);
        jsm.send(message);
    }
    public void sendShortlisted(String to) throws MessagingException {
        MimeMessage message = jsm.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Shortlisted");
        String body="""
                <html>
                <body>
                    <h2>Congratulations!</h2>
                    <p>Dear Candidate,</p>
                    <p>We are pleased to inform you that you have been shortlisted for the next round of interviews.</p>
                    <p>Our recruitment team will contact you with further details.</p>
                    <p>Regards,<br>HR Team</p>
                </body>
                </html>
                """;
        helper.setText(body, true);

        jsm.send(message);
    }
    public void sendSelected(String to) throws MessagingException {
        MimeMessage message = jsm.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Selected");
        String body="""
                <html>
                <body>
                    <h2>Welcome Onboard!</h2>
                    <p>Dear Candidate,</p>
                    <p>We are delighted to inform you that you have been selected for the position.</p>
                    <p>Our HR team will reach out with onboarding instructions.</p>
                    <p>Regards,<br>HR Team</p>
                </body>
                </html>
                """;
        helper.setText(body,true);

        jsm.send(message);
    }
}
