package concurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnBaseDatosLibretaText implements LibretaTex {

    @Override
    public void inscribir(String p, String c) {
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (Connection conn = Coneccion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO Participaciones (DNI, ConcursoID, Fecha) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, p);
            preparedStatement.setString(2, c);
            preparedStatement.setString(3, hoy.format(formatter));
            preparedStatement.executeUpdate();
            System.out.println("Inscripción creada exitosamente en la base de datos.");
        } catch (SQLException e) {
            throw new RuntimeException("no se podo conectar a base de datos ", e);
        }
    }

    @Override
    public boolean estaInscripto(String p) {

        String SELECT_INSCRIPCION_SQL = "SELECT * FROM Participaciones WHERE DNI = ?";

        try (Connection conn = Coneccion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_INSCRIPCION_SQL)) {
            preparedStatement.setString(1, p);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Si hay algún resultado, significa que el participante está inscrito
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("no se pudo acceder a la base de datos ");
            // En caso de excepción, asumimos que no está inscrito
        }
    }
}
