package concurso;

import java.util.ArrayList;

public class Participantes {

    @SuppressWarnings("rawtypes")
    public ArrayList<Participante> all() {
        // retorna lista de participantes
        @SuppressWarnings("unchecked")
        ArrayList<Participante> participantes = new ArrayList();
        participantes.add(new Participante("jose", "35123011"));
        participantes.add(new Participante("pepito", "35101984"));
        participantes.add(new Participante("pepita ", "35123456"));
        return participantes;

    }

}
