package concurso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EnDiscoLibretaDeText implements LibretaTex {
    String rutaDisco;

    public EnDiscoLibretaDeText(String rutaDisco) {
        this.rutaDisco = rutaDisco;
    }

    public void inscribir(String p, String c) {

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String linea = hoy.format(formatter) + ", " + p + ", " + c + "\n";

        File archivo = new File(rutaDisco);

        if (archivo.exists()) {
            try (FileWriter writer = new FileWriter(rutaDisco, true)) {
                writer.write(linea + "\n");
                System.out.println("Inscripción agregada al archivo.");
            } catch (IOException e) {
                throw new RuntimeException("no se pudo inscribir en disco", e);

            }

        } else {
            // Si el archivo no existe, crearlo y agregar la línea
            try (FileWriter writer = new FileWriter(rutaDisco)) {
                writer.write(linea + "\n");
                System.out.println("Archivo creado y primera inscripción agregada.");
            } catch (IOException e) {
                throw new RuntimeException("no se pudo inscribir en disco", e);

            }
        }
    }

    public boolean estaInscripto(String p) {
        File archivo = new File(rutaDisco);
        List<String> lineasExistentes;
        try {
            lineasExistentes = Files.readAllLines(Paths.get(rutaDisco));
            if (archivo.exists())
                return lineasExistentes.stream().anyMatch(linea1 -> linea1.contains(p));
            else
                return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("no se pudo inscribir en disco", e);
        }

    }
}
