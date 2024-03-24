package concurso;

import java.time.LocalDate;
import java.util.ArrayList;

public class Concurso {
    @SuppressWarnings("unused")
    private final String nombre;
    private final LocalDate fechaInicioConcurso;
    private final LocalDate fechaFinConcurso;
    private ArrayList<Participante> participantes = new ArrayList<>();

    public Concurso(String nombre, LocalDate fechaInicioConcurso, LocalDate fechaFinConcurso) {
        this.nombre = nombre;
        this.fechaInicioConcurso = fechaInicioConcurso;
        this.fechaFinConcurso = fechaFinConcurso;
    }

    /**
     * @param p
     */
    public void inscripcion(Participante p) {
        // comparar si el participante ya esta inscripto
        if (this.participantes.contains(p))
            System.out.println("El  participante ya se encuentra registrado");
        // comprobar si esta dentro de rango de fechas del concurso y inscribirlo
        else {
            if (!fechaInicioConcurso.isAfter(LocalDate.now()) && !fechaFinConcurso.isBefore(LocalDate.now())) {
                this.participantes.add(p);
                // sumar 10 puntos al participante si se inscribio en el primer dia
                if (LocalDate.now().equals(fechaInicioConcurso))
                    p.agregarPuntos(10);
                else
                    System.out.println("No se puede inscribir a un concurso que no ha comenzado todavía");
            }
        }
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

}
