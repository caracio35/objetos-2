package ar.unrn.tp4;

// Capa de acceso a datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ParticipanteDAO {
    private Connection dbConn;

    public ParticipanteDAO() {
        try {
            setupBaseDeDatos();
        } catch (SQLException e) {
            // Manejar excepciones
        }
    }

    private void setupBaseDeDatos() throws SQLException {
         String url = "jdbc:jdbc:mysql://localhost:3306/participantes";
        String user = "root";
        String password = "";
        Connection conn = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarParticipante(String nombre, String telefono, String region) throws SQLException {
        // Lógica para agregar un participante a la base de datos
        // ...
    }
}
