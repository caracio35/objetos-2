import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import concurso.Concurso;
import concurso.EnBaseDatosLibretaText;
import concurso.EnDiscoLibretaDeText;
import concurso.MokEnDiscoLibretaText;
import concurso.Participante;

public class ConcursoTest {
    @Test
    public void unParticipateInscripto() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15),
                mokLibreta);
        Participante pepe = new Participante("pepe", "35123011");
        concuTest.inscripcion(pepe);
        assertTrue(concuTest.participa(pepe));
        System.out.println(pepe.cuantosPuntos());

    }

    @Test
    public void inscriptoPrimerDia() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now(), LocalDate.now().plusDays(15), mokLibreta);
        Participante pepe = new Participante("pepe", "35123011");
        concuTest.inscripcion(pepe);
        System.out.println(pepe.cuantosPuntos());
        assertTrue(concuTest.participa(pepe));
        assertEquals(10, pepe.cuantosPuntos());
    }

    @Test
    public void inscriptoFueraRango() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Concurso concuTestDespues = new Concurso("concursoTest", LocalDate.now().minusDays(7),
                LocalDate.now().minusDays(1), mokLibreta);
        Concurso concutestAntes = new Concurso("concuters", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(6), mokLibreta);
        Participante juan = new Participante("Juan", "987654321");
        concuTestDespues.inscripcion(juan);
        assertFalse(concuTestDespues.participa(juan));
        concutestAntes.inscripcion(juan);
        assertFalse(concutestAntes.participa(juan));

    }

    @Test
    public void inscripcionDespuesPrimerDia() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15),
                mokLibreta);
        Participante pepe = new Participante("pepe", "35123011");
        concuTest.inscripcion(pepe);
        assertTrue(concuTest.participa(pepe));
        System.out.println(pepe.cuantosPuntos());
        assertEquals(0, pepe.cuantosPuntos());
    }

    @Test
    public void incripccionEnLibreta() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Participante pepe = new Participante("pepe", "35123019");
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15),
                mokLibreta);
        pepe = new Participante("clara ", "55101984");
        concuTest.inscripcion(pepe);
        assertTrue(mokLibreta.estaInscripto(pepe.getDni()));
        pepe = new Participante("pepe", "35101984");
        assertFalse(mokLibreta.estaInscripto(pepe.getDni()));
    }

    @Test
    public void inscriveEnDisco() {
        EnDiscoLibretaDeText disco = new EnDiscoLibretaDeText("/home/jose/objetos-2/prueva.txt");
        Participante pepe = new Participante("pepe", "35123019");
        Concurso concuTest = new Concurso("concursoTest",
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), disco);
        pepe = new Participante("clara ", "55101984");
        concuTest.inscripcion(pepe);
        assertTrue(disco.estaInscripto(pepe.getDni()));
        pepe = new Participante("pepe", "35101984");
        assertFalse(disco.estaInscripto(pepe.getDni()));
    }

    @Test
    public void inscriveEnBaseDeDatos() {

        EnBaseDatosLibretaText baseDato = new EnBaseDatosLibretaText();
        Participante pepe = new Participante("pepe", "35123019");
        Concurso concuTest = new Concurso("concursoTest",
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), baseDato);
        pepe = new Participante("clara ", "55101984");
        concuTest.inscripcion(pepe);
        assertTrue(baseDato.estaInscripto(pepe.getDni()));
        pepe = new Participante("pepe", "35101984");
        assertFalse(baseDato.estaInscripto(pepe.getDni()));
    }
}
