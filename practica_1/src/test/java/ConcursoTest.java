import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import concurso.Concurso;
import concurso.EnBaseDatosLibretaText;
import concurso.EnDiscoLibretaDeText;
import concurso.EnvioDeMailConcursantes;
import concurso.MokEnDiscoLibretaText;
import concurso.Participante;

public class ConcursoTest {
    @Test
    public void unParticipateInscripto() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();

        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15),
                mokLibreta, new EnvioDeMailConcursantes());
        Participante pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        concuTest.inscripcion(pepe);
        assertTrue(concuTest.participa(pepe));
        System.out.println(pepe.cuantosPuntos());

    }

    @Test
    public void inscriptoPrimerDia() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now(), LocalDate.now().plusDays(15), mokLibreta,
                new EnvioDeMailConcursantes());
        Participante pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        concuTest.inscripcion(pepe);
        System.out.println(pepe.cuantosPuntos());
        assertTrue(concuTest.participa(pepe));
        assertEquals(10, pepe.cuantosPuntos());
    }

    @Test
    public void inscriptoFueraRango() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Concurso concuTestDespues = new Concurso("concursoTest", LocalDate.now().minusDays(7),
                LocalDate.now().minusDays(1), mokLibreta, new EnvioDeMailConcursantes());
        Concurso concutestAntes = new Concurso("concuters", LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(6), mokLibreta, new EnvioDeMailConcursantes());
        Participante juan = new Participante("juan", "35123011", "caracio35@gmail.com");
        concuTestDespues.inscripcion(juan);
        assertFalse(concuTestDespues.participa(juan));
        concutestAntes.inscripcion(juan);
        assertFalse(concutestAntes.participa(juan));

    }

    @Test
    public void inscripcionDespuesPrimerDia() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15),
                mokLibreta, new EnvioDeMailConcursantes());
        Participante pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        concuTest.inscripcion(pepe);
        assertTrue(concuTest.participa(pepe));
        System.out.println(pepe.cuantosPuntos());
        assertEquals(0, pepe.cuantosPuntos());
    }

    @Test
    public void incripccionEnLibreta() {
        MokEnDiscoLibretaText mokLibreta = new MokEnDiscoLibretaText();
        Participante pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        Concurso concuTest = new Concurso("concursoTest", LocalDate.now().minusDays(1), LocalDate.now().plusDays(15),
                mokLibreta, new EnvioDeMailConcursantes());
        pepe = new Participante("clara ", "55101984", "clara@gmail.com");
        concuTest.inscripcion(pepe);
        assertTrue(mokLibreta.estaInscripto(pepe.getDni()));
        pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        assertFalse(mokLibreta.estaInscripto(pepe.getDni()));
    }

    @Test
    public void inscriveEnDisco() {
        EnDiscoLibretaDeText disco = new EnDiscoLibretaDeText("/home/jose/objetos-2/prueva.txt");
        Participante pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        Concurso concuTest = new Concurso("concursoTest",
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), disco, new EnvioDeMailConcursantes());
        pepe = new Participante("clara ", "55101984", "clara@gmail.com");
        concuTest.inscripcion(pepe);
        assertTrue(disco.estaInscripto(pepe.getDni()));
        pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        assertFalse(disco.estaInscripto(pepe.getDni()));
    }

    @Test
    public void inscriveEnBaseDeDatos() {

        EnBaseDatosLibretaText baseDato = new EnBaseDatosLibretaText();
        Participante pepe = new Participante("pepe", "35123011", "caracio35@gmail.com");
        Concurso concuTest = new Concurso("concursoTest",
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(15), baseDato, new EnvioDeMailConcursantes());
        pepe = new Participante("clara ", "55101984", "clara@gmail.com");
        concuTest.inscripcion(pepe);
        assertTrue(baseDato.estaInscripto(pepe.getDni()));
        pepe = new Participante("clara ", "55101984", "clara@gmail.com");
        assertFalse(baseDato.estaInscripto(pepe.getDni()));
    }

    @Test
    public void envioDeMail() {
        EnvioDeMailConcursantes.envioDeMail("caracio35@gmail.com");
    }
}
