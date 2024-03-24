import org.junit.jupiter.api.Test;

import concurso.Concurso;
import concurso.Participante;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ConcursoTest {
    @Test
    public void unParticipateInscripto() {

        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15));
        Participante pepe = new Participante("pepe", "35123011");
        concuTest.inscripcion(pepe);
        assertTrue(concuTest.participa(pepe));
        System.out.println(pepe.cuantosPuntos());

    }

    @Test
    public void inscriptoPrimerDia() {
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now(), LocalDate.now().plusDays(15));
        Participante pepe = new Participante("pepe", "35123011");
        concuTest.inscripcion(pepe);
        System.out.println(pepe.cuantosPuntos());
        assertTrue(concuTest.participa(pepe));
        assertEquals(10, pepe.cuantosPuntos());
    }

    @Test
    public void inscriptoFueraRango() {
        Concurso concuTestDespues = new Concurso("concursoTest", LocalDate.now().minusDays(7),
                LocalDate.now().minusDays(1));
        Concurso concutestAntes = new Concurso("concuters", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(6));
        Participante juan = new Participante("Juan", "987654321");
        concuTestDespues.inscripcion(juan);
        assertFalse(concuTestDespues.participa(juan));
        concutestAntes.inscripcion(juan);
        assertFalse(concutestAntes.participa(juan));

    }

    @Test
    public void inscripcionDespuesPrimerDia() {
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15));
        Participante pepe = new Participante("pepe", "35123011");
        concuTest.inscripcion(pepe);
        assertTrue(concuTest.participa(pepe));
        System.out.println(pepe.cuantosPuntos());
        assertEquals(0, pepe.cuantosPuntos());
    }
}
