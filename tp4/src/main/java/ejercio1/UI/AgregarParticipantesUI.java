package ejercio1.UI;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ejercio1.modelo.AplicacionRegistracionParticipantes;


public class AgregarParticipantesUI extends JFrame {
    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;
    AplicacionRegistracionParticipantes aplicacion;

    public AgregarParticipantesUI(AplicacionRegistracionParticipantes aplicacion) {
        this.aplicacion = aplicacion;
        setupUIComponents();
    }

    public void setupUIComponents() {
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
                onBotonCargar();
            }
        });
        contentPane.add(botonCargar);
        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }

    private void onBotonCargar() {
        String nombreStr = this.nombre.getText();
        String telefonoStr = this.telefono.getText();
        String regionStr = this.region.getText();
        try {
            aplicacion.agregarParticipantes(nombreStr, telefonoStr, regionStr);
            JOptionPane.showMessageDialog(this, "Participante agregado correctamente");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el participante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
