package example.micronaut;

import com.sendgrid.Personalization;
import com.sendgrid.Content;
import com.sendgrid.Mail;
import com.sendgrid.SendGrid;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.Method;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
@Requires(condition = SendGridEmailCondition.class)
class SendGridEmailService implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SendGridEmailService.class);

    protected final String apiKey;

    protected final String fromEmail;

    SendGridEmailService(@Value("${SENDGRID_APIKEY:none}") String apiKeyEnv,
                         @Value("${SENDGRID_FROM_EMAIL:none}") String fromEmailEnv,
                         @Value("${sendgrid.apikey:none}") String apiKeyProp,
                         @Value("${sendgrid.fromemail:none}") String fromEmailProp) {
        this.apiKey = apiKeyEnv != null && !apiKeyEnv.equals("none") ? apiKeyEnv : apiKeyProp;
        this.fromEmail = fromEmailEnv != null && !fromEmailEnv.equals("none")  ? fromEmailEnv: fromEmailProp;
    }

    protected Content contentOfEmail(Email email) {
        if ( email.getHtmlBody() !=null ) {
            return new Content("text/html", email.getHtmlBody());
        }
        if ( email.getTextBody() !=null ) {
            return new Content("text/plain", email.getTextBody());
        }

        return null;
    }

    @Override
    public void send(Email email) {

        Personalization personalization = new Personalization();
        personalization.setSubject(email.getSubject());

        com.sendgrid.Email to = new com.sendgrid.Email(email.getRecipient());
        personalization.addTo(to);

        if ( email.getCc() != null ) {
            for ( String cc : email.getCc() ) {
                com.sendgrid.Email ccEmail = new com.sendgrid.Email();
                ccEmail.setEmail(cc);
                personalization.addCc(ccEmail);
            }
        }

        if ( email.getBcc()  != null ) {
            for ( String bcc : email.getBcc() ) {
                com.sendgrid.Email bccEmail = new com.sendgrid.Email();
                bccEmail.setEmail(bcc);
                personalization.addBcc(bccEmail);
            }
        }

        Mail mail = new Mail();
        com.sendgrid.Email from = new com.sendgrid.Email();
        from.setEmail(fromEmail);
        mail.from = from;
        mail.addPersonalization(personalization);
        Content content = contentOfEmail(email);
        mail.addContent(content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            if (LOG.isInfoEnabled()) {
                LOG.info("Status Code: {}", String.valueOf(response.getStatusCode()));
                LOG.info("Body: {}", response.getBody());
                for ( String k : response.getHeaders().keySet()) {
                    String v = response.getHeaders().get(k);
                    LOG.info("Response Header {} => {}", k, v);
                }
            }


        } catch (IOException ex) {
            if (LOG.isErrorEnabled()) {
                LOG.error(ex.getMessage());
            }
        }
    }
}