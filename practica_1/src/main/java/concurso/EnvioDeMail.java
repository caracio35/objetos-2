package concurso;

public interface EnvioDeMail {

    public void sendEmail(String recipient, String subject, String body);

    public String envioDeMail(String dirMail);
}
