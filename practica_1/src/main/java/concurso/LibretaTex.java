package concurso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class LibretaTex {

    public void inscribir(Participante p, Concurso c, String rutaArchivo) throws IOException {

        // Obtener la fecha y hora actual y cambiar a local date

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String linea = hoy.format(formatter) + ", " + p.getDni() + ", " + c.nombreConcurso() + "\n";

        File archivo = new File(rutaArchivo);

        if (archivo.exists()) {
            List<String> lineasExistentes = Files.readAllLines(Paths.get(rutaArchivo));
            // Verificar si el DNI del participante ya está en el archivo
            // boolean estaRegistrado = lineasExistentes.stream().anyMatch(linea1 ->
            // linea1.contains(p.getDni()));
            if (!lineasExistentes.stream().anyMatch(linea1 -> linea1.contains(p.getDni()))) {
                // Si el DNI no está en el archivo, agregar al final
                try (FileWriter writer = new FileWriter(rutaArchivo, true)) {
                    writer.write(linea + "\n");
                    System.out.println("Inscripción agregada al archivo.");
                }
            } else {
                System.out.println("El participante con DNI " + p.getDni() + " ya está registrado en el concurso.");
            }
        } else {
            // Si el archivo no existe, crearlo y agregar la línea
            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                writer.write(linea + "\n");
                System.out.println("Archivo creado y primera inscripción agregada.");
            }
        }
    }

    public boolean estaInscripto(String rutaArchivo, Participante p) throws IOException {
        List<String> lineasExistentes = Files.readAllLines(Paths.get(rutaArchivo));
        File archivo = new File(rutaArchivo);
        if (archivo.exists())
            return lineasExistentes.stream().anyMatch(linea1 -> linea1.contains(p.getDni()));
        else
            return false;
    }
}
