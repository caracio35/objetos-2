package ar.unrn.tp4;

// Capa de presentación (UI)
import java.awt.EventQueue;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AgregarParticipanteUI ui = new AgregarParticipanteUI();
                    ParticipanteController controller = new ParticipanteController();
                    ui.setController(controller);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}
