package concurso;

import java.util.Objects;

public class Participante {
    private String name;
    private String dni;
    private int puntos;

    public Participante(String name, String dni) {
        this.name = name;
        this.dni = dni;
        puntos = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Participante that))
            return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public void agregarPuntos(int i) {
        puntos += i;
    }

    public int cuantosPuntos() {
        return puntos;
    }
}
