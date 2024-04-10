package concurso;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EnvioDeMailConcursantes {
    String host;
    String port;
    String username;
    String password;

    public EnvioDeMailConcursantes() {

        // Configuración del servidor de correo saliente (SMTP) de Mailtrap
        String host = "sandbox.smtp.mailtrap.io";
        String port = "2525"; // Puerto SMTP de Mailtrap

        // Credenciales de autenticación para Mailtrap
        String username = "c1ba52ecc2e528";
        String password = "0d96973aaf39e7";
    }

    public static void sendEmail(String recipient, String subject, String body) {
        // Configuración del servidor de correo saliente (SMTP) de Mailtrap
        String host = "sandbox.smtp.mailtrap.io";
        String port = "2525"; // Puerto SMTP de Mailtrap

        // Credenciales de autenticación para Mailtrap
        String username = "c1ba52ecc2e528";
        String password = "0d96973aaf39e7";

        // Propiedades para la sesión de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Crear una sesión de correo con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear un mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            // Enviar el mensaje de correo
            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito a " + recipient);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }

    public static void envioDeMail(String dirMail) {
        String recipient = dirMail;
        String subject = "¡Te has registrado en nuestro concurso!";
        String body = "¡Gracias por registrarte en nuestro concurso! Esperamos que tengas una experiencia increíble.";

        sendEmail(recipient, subject, body);
    }

}
