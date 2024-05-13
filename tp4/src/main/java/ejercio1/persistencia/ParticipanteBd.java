package ejercio1.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ejercio1.modelo.ListaParticipantes;
import ejercio1.modelo.Participante;

public class ParticipanteBd implements ListaParticipantes {
    private final String url = "jdbc:mysql://localhost:3306/Participantes";
    private final String user = "root";
    private final String password = "";
    private Connection dbConn;


    @Override
    public void addParticipante(Participante p) {
        
        if (p.cualEsTuNombre().isEmpty()) {
            throw new IllegalArgumentException("Debe cargar un nombre");
        }
        if (p.cualEsTuNumTelefono().isEmpty()) {
            throw new IllegalArgumentException("Debe cargar un teléfono");
        }
        if (!validarTelefono(p.cualEsTuNumTelefono())) {
            throw new IllegalArgumentException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
        }
        if (!p.cualEsTuReguion().equals("China") && !p.cualEsTuReguion().equals("US")
                && !p.cualEsTuReguion().equals("Europa")) {
            throw new IllegalArgumentException("Región desconocida. Las conocidas son: China, US, Europa");
        }
        
        try (Connection conn = conectar();
            PreparedStatement st = conn.prepareStatement("INSERT INTO participantes(nombre, telefono, region) VALUES (?, ?, ?)")) {
            st.setString(1, p.cualEsTuNombre());
            st.setString(2, p.cualEsTuNumTelefono());
            st.setString(3, p.cualEsTuReguion());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("no se pudo conectar a la base de datos ", e);
        }
           
    }
    

    

   

    @Override
    public Participante recuperarParticipante(Participante p) {
        Participante p1;
        try (Connection conn = conectar();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM participantes WHERE nombre =?")) {
                st.setString(1, p.cualEsTuNombre());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    String region = rs.getString("region");
                    p1 = new Participante(nombre, telefono, region);
                    return p1;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException("no se pudo conectar a la base de datos ", e);
            }

    }

    @Override
    public ArrayList<Participante> getParticipantes() {
        Participante p;
         try (Connection conn = conectar();
         PreparedStatement st = conn.prepareStatement("SELECT * FROM participantes")) {
            ResultSet rs = st.executeQuery();
            ArrayList<Participante> participantes = new ArrayList<>();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String region = rs.getString("region");
                p = new Participante(nombre, telefono, region);
                participantes.add(p);
                st.close();
            }
            return participantes;
        } catch (SQLException e) {
            throw new RuntimeException("no se pudo conectar a la base de datos ", e);

        }
    }

    private Connection conectar() {
        try {
            this.dbConn = DriverManager.getConnection(url, user, password);
            return this.dbConn;
        } catch (SQLException e) {
            throw new RuntimeException("no se pudo conectar");
        }

    }
     private boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }
    
}
