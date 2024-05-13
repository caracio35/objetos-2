import ejercio1.UI.AgregarParticipantesUI;
import ejercio1.modelo.AplicacionRegistracionParticipantes;
import ejercio1.persistencia.ParticipanteBd;

public class Main {
    public static void main(String[] args) {
        launchApplication();
    }

    private static void launchApplication() {
        try {
            ParticipanteBd aplicacionBd = new ParticipanteBd(); // Declare and initialize the variable
            AplicacionRegistracionParticipantes AplicacionRegistracionParticipantes = new AplicacionRegistracionParticipantes(
                    aplicacionBd);
            AgregarParticipantesUI UIpar = new AgregarParticipantesUI(AplicacionRegistracionParticipantes);
            UIpar.setupUIComponents();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

