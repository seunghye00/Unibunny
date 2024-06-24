package commons;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
//이메일 인증 
public class MailSender {
	public MailSender() {};
    private static final String type = "text/html; charset=utf-8";
    private static final String myEmail = "unibunnyhelp@gmail.com";
    private static final String authCode = "lokediwmwigbvnfg";
    private String target_email;
    private String mail_title;
    private String mail_content;

    public MailSender(String emailAddress, String title, String content) {
        this.target_email = emailAddress;
        this.mail_title = title;
        this.mail_content = content;
    }

    public boolean Send() {
        boolean isSend = false;

        Properties props = getProperties();
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, authCode);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail, "Authentication"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(target_email));
            message.setSubject("[UniBunny] " + mail_title);
            message.setContent(mail_content, type);

            Transport.send(message);
            isSend = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSend = false;
        }
        return isSend;
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        return props;
    }
}