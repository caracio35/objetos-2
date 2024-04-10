package concurso;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Concurso {
    @SuppressWarnings("unused")
    private final String nombre;
    private final LocalDate fechaInicioConcurso;
    private final LocalDate fechaFinConcurso;
    private ArrayList<Participante> participantes = new ArrayList<>();
    private LibretaTex libreta;
    EnvioDeMail email;

    public Concurso(String nombre, LocalDate fechaInicioConcurso, LocalDate fechaFinConcurso, LibretaTex libreta,
            EnvioDeMail email) {
        this.nombre = nombre;
        this.fechaInicioConcurso = fechaInicioConcurso;
        this.fechaFinConcurso = fechaFinConcurso;
        this.libreta = libreta;
        this.email = email;

    }

    /**
     * @param p
     * @throws IOException
     */
    @SuppressWarnings("static-access")
    public void inscripcion(Participante p) {
        // comparar si el participante ya esta inscripto
        if (this.participantes.contains(p))
            // sacar los sysout del modelo lansar exepciones
            // sYtem.out.println("El participante ya se encuentra registrado");
            throw new RuntimeException("El  participante ya se encuentra registrado");
        // comprobar si esta dentro de rango de fechas del concurso y inscribirlo

        if (!fechaInicioConcurso.isAfter(LocalDate.now()) && !fechaFinConcurso.isBefore(LocalDate.now())) {
            this.participantes.add(p);
            this.libreta.inscribir(p.getDni(), nombre);
            email.envioDeMail(p.cualEsTuMail());
            // sumar 10 puntos al participante si se inscribio en el primer dia
            if (LocalDate.now().equals(fechaInicioConcurso))
                p.agregarPuntos(10);

        } else
            throw new RuntimeException("No se puede inscribir a un concurso que no ha comenzado todavía");
    }

    public boolean eliminarInscripción(Participante p) {
        int posicion = this.participantes.indexOf(p);
        if (posicion == -1)
            return false;
        else {
            this.participantes.remove(posicion);
            return true;
        }
    }

    public void imprimirInscritos() {
        for (int i = 0; i < this.participantes.size(); i++)
            System.out.println((i + 1) + ". " + this.participantes.get(i).toString());
        for (Participante p : this.participantes) {
            System.out.println(p.toString());
        }
    }

    public boolean participa(Participante p) {
        return this.participantes.contains(p);
    }

    public void inscripcionLibreta(Participante p) {
        // a cada participante lo busca en un archivo de texto y si no esta lo inscibe y
        // lo guarda

    }

    String nombreConcurso() {
        return nombre;
    }
}
