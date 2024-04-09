package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EnBaseDatosLibroVentasDiario implements LibroVentaDiario {

    @Override
    public void agregarVenta(String monto) {
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (Connection conn = Coneccion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO Ventas (MontoVenta, DiaVenta) VALUES (?, ?)")) {
            preparedStatement.setString(1, monto);
            preparedStatement.setString(2, hoy.format(formatter));
            preparedStatement.executeUpdate();
            Coneccion.closeConnection(conn);
            System.out.println("Venta creada exitosamente en la base de datos.");
        } catch (SQLException e) {
            throw new RuntimeException("no se pudo conectar a la base de datos ", e);
        }
    }

    @Override
    public boolean estaEstaVenta(String monto) {

        final String SELECT_ALL_VENTAS_SQL = "SELECT MontoVenta FROM Ventas";

        List<String> montos = new ArrayList<>();
        try (Connection conn = Coneccion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_VENTAS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String montoVenta = resultSet.getString("MontoVenta");
                montos.add(montoVenta);
            }
            Coneccion.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException("no se pudo conectar a la base de datos ", e);
        }

        for (String string : montos) {
            if (string.equals(monto)) {

                return true;
            }
        }

        return false;

    }
}
