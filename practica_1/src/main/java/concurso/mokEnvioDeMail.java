package concurso;

public class mokEnvioDeMail implements EnvioDeMail {
    public mokEnvioDeMail() {

    }

    public void sendEmail(String recipient, String subject, String body) {

    }

    @Override
    public String envioDeMail(String dirMail) {
        return "se envio un mail a " + dirMail;
    }

}
