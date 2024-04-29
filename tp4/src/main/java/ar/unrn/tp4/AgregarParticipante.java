package ar.unrn.tp4;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AgregarParticipante extends JFrame {
    private Connection dbConn;
    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;

    public AgregarParticipante() throws SQLException {
        setupBaseDeDatos();
        setupUIComponents();
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

    private void setupUIComponents() {
        setTitle("Add Participant");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nombre = new JTextField(10);
        this.telefono = new JTextField(10);
        this.region = new JTextField(10);
        this.nombre.setText("");
        this.telefono.setText("");
        this.region.setText("China");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Nombre: "));
        contentPane.add(nombre);
        contentPane.add(new JLabel("Telefono: "));
        contentPane.add(telefono);
        contentPane.add(new JLabel("Region: "));
        contentPane.add(region);
        JButton botonCargar = new JButton("Cargar");
        botonCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onBotonCargar();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(AgregarParticipante.this,
                            "Error al cargar el participante: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(botonCargar);
        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }

    private void onBotonCargar() throws SQLException {
        if (nombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe cargar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (telefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe cargar un teléfono", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validarTelefono(telefono.getText())) {
            JOptionPane.showMessageDialog(this, "El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!region.getText().equals("China") && !region.getText().equals("US") && !region.getText().equals("Europa")) {
            JOptionPane.showMessageDialog(this, "Región desconocida. Las conocidas son: China, US, Europa", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        PreparedStatement st = dbConn
                .prepareStatement("INSERT INTO participantes(nombre, telefono, region) VALUES (?, ?, ?)");
        try {
            st.setString(1, nombre.getText());
            st.setString(2, telefono.getText());
            st.setString(3, region.getText());
            st.executeUpdate();
        } finally {
            st.close();
            dbConn.close(); // Cerrar la conexión después de usarla
        }
        dispose();
    }

    private boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    public static void main(String[] args) {
        try {
            new AgregarParticipante();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
