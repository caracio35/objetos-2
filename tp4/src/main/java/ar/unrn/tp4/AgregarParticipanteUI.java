package ar.unrn.tp4;

// Capa de presentación (UI)
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AgregarParticipanteUI extends JFrame {
    private ParticipanteController controller;
    private JTextField nombreField;
    private JTextField telefonoField;
    private JTextField regionField;

    public AgregarParticipanteUI() {
        setTitle("Agregar Participante");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupUIComponents();
        setVisible(true);
    }

    private void setupUIComponents() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(4, 2));

        JLabel lblNombre = new JLabel("Nombre: ");
        nombreField = new JTextField(10);
        contentPane.add(lblNombre);
        contentPane.add(nombreField);

        JLabel lblTelefono = new JLabel("Teléfono: ");
        telefonoField = new JTextField(10);
        contentPane.add(lblTelefono);
        contentPane.add(telefonoField);

        JLabel lblRegion = new JLabel("Región: ");
        regionField = new JTextField("China", 10);
        contentPane.add(lblRegion);
        contentPane.add(regionField);

        JButton btnCargar = new JButton("Cargar");
        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onBotonCargar();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(AgregarParticipanteUI.this,
                            "Error al cargar el participante: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnCargar);

        setContentPane(contentPane);
    }

    public void setController(ParticipanteController controller) {
        this.controller = controller;
    }

    private void onBotonCargar() throws SQLException {
        String nombre = nombreField.getText();
        String telefono = telefonoField.getText();
        String region = regionField.getText();

        // Validar los datos
        if (nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y Teléfono son obligatorios", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al método del controlador para agregar el participante
        controller.agregarParticipante(nombre, telefono, region);
        JOptionPane.showMessageDialog(this, "Participante agregado exitosamente", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        // Limpiar campos después de agregar el participante
        nombreField.setText("");
        telefonoField.setText("");
        regionField.setText("China");
    }
}
