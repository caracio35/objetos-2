package ar.unrn.tp4;

// Capa de lógica de negocio
import java.sql.SQLException;

public class ParticipanteController {
    private final ParticipanteDAO participanteDAO;

    public ParticipanteController() {
        this.participanteDAO = new ParticipanteDAO();
    }

    public void agregarParticipante(String nombre, String telefono, String region) throws SQLException {
        // Validar datos
        // ...

        // Llamar a la capa de acceso a datos para agregar el participante
        participanteDAO.agregarParticipante(nombre, telefono, region);
    }
}
