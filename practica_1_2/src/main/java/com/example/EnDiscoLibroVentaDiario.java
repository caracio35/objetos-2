package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EnDiscoLibroVentaDiario implements LibroVentaDiario {
    String ubicacionEndisco;

    public EnDiscoLibroVentaDiario(String ubicacionEnDisco) {
        this.ubicacionEndisco = ubicacionEnDisco;
    }

    @Override
    public void agregarVenta(String monto) {
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String linea = hoy.format(formatter) + "||" + monto + "\n";

        File archivo = new File(ubicacionEndisco);

        if (archivo.exists()) {
            try (FileWriter writer = new FileWriter(ubicacionEndisco, true)) {
                writer.write(linea + "\n");
                System.out.println("venta agregada al archivo.");
            } catch (IOException e) {
                throw new RuntimeException("no se pudo agregar venta en disco", e);

            }

        } else {
            // Si el archivo no existe, crearlo y agregar la línea
            try (FileWriter writer = new FileWriter(ubicacionEndisco)) {
                writer.write(linea + "\n");
                System.out.println("Archivo creado y primera venta agregada.");
            } catch (IOException e) {
                throw new RuntimeException("no se pudo agregar venta en disco", e);

            }
        }
    }

    @Override
    public boolean estaEstaVenta(String monto) {
        File archivo = new File(ubicacionEndisco);
        List<String> lineasExistentes;
        try {
            lineasExistentes = Files.readAllLines(Paths.get(ubicacionEndisco));
            if (archivo.exists())
                return lineasExistentes.stream().anyMatch(linea1 -> linea1.contains(monto));
            else
                return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("no se pudo acceder a disco", e);
        }

    }
}
