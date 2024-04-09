import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.Bebida;
import com.example.EnBaseDatosLibroVentasDiario;
import com.example.EnDiscoLibroVentaDiario;
import com.example.Mesa;
import com.example.MokLibroVenta;
import com.example.Plato;
import com.example.TarComarcaPlus;
import com.example.TarMaster;
import com.example.TarViedma;
import com.example.TarVisa;

public class TestCobros {
        @Test
        public void testTarNoEstaVencida() {
                var tarjeta = new TarVisa(111112, "pepe", 500.00,
                                LocalDate.now().plusMonths(20));
                assumeTrue(tarjeta.estaActiva());
        }

        @Test
        public void calculoCostoVisa() {
                var libroDiario = new MokLibroVenta();
                var tarjeta = new TarVisa(111112, "pepe", 500.00, LocalDate.now().plusMonths(120));
                var mesa = new Mesa(1, libroDiario);
                Plato p1 = new Plato(null, null, 50.00),
                                p2 = new Plato("Pizza", "Marinara", 30.00),
                                p3 = new Plato("Hamburguesa", "Chimichurri", 40.00);

                Bebida b1 = new Bebida("Cerveza", 50), b2 = new Bebida("Vino", 70), b3 = new Bebida("Agua", 40);

                mesa.agregarBebida(b1);
                mesa.agregarBebida(b2);
                mesa.agregarBebida(b3);
                mesa.agregarBebida(b2);
                mesa.agregarPlato(p1);
                mesa.agregarPlato(p2);
                mesa.agregarPlato(p3);
                assertTrue(mesa.pagarMontoTotal(tarjeta, 0) == 343.10);
        }

        // 343.10
        @Test
        public void calculoCostoMaster() {
                var libroDiario = new MokLibroVenta();
                LocalDate hoy = LocalDate.now();
                var tarjeta = new TarMaster(111112, "pepe", 500.00, hoy.plusDays(30)); // $100 limit
                var mesa = new Mesa(1, libroDiario);
                Plato p1 = new Plato(null, null, 50.00),
                                p2 = new Plato("Pizza", "Marinara", 30.00),
                                p3 = new Plato("Hamburguesa", "Chimichurri", 40.00);

                Bebida b1 = new Bebida("Cerveza", 50), b2 = new Bebida("Vino", 70), b3 = new Bebida("Agua", 40);

                mesa.agregarBebida(b1);
                mesa.agregarBebida(b2);
                mesa.agregarBebida(b3);
                mesa.agregarBebida(b2);
                mesa.agregarPlato(p1);
                mesa.agregarPlato(p2);
                mesa.agregarPlato(p3);
                assertTrue((mesa.pagarMontoTotal(tarjeta, 0)) == 347.6);
                assertTrue(libroDiario.estaEstaVenta("347.6"));
        }

        @Test
        public void calculoComarcaPlus() {
                var libroDiario = new MokLibroVenta();
                LocalDate hoy = LocalDate.now();
                var tarjeta = new TarComarcaPlus(111112, "pepe", 500.00, hoy.plusDays(30)); // $100 limit
                var mesa = new Mesa(1, libroDiario);
                Plato p1 = new Plato(null, null, 50.00),
                                p2 = new Plato("Pizza", "Marinara", 30.00),
                                p3 = new Plato("Hamburguesa", "Chimichurri", 40.00);

                Bebida b1 = new Bebida("Cerveza", 50), b2 = new Bebida("Vino", 70), b3 = new Bebida("Agua", 40);

                mesa.agregarBebida(b1);
                mesa.agregarBebida(b2);
                mesa.agregarBebida(b3);
                mesa.agregarBebida(b2);
                mesa.agregarPlato(p1);
                mesa.agregarPlato(p2);
                mesa.agregarPlato(p3);
                assertTrue((mesa.pagarMontoTotal(tarjeta, 0)) == 343);
        }

        @Test
        public void calculoDemasTar() {
                var libroDiario = new MokLibroVenta();
                LocalDate hoy = LocalDate.now();
                var tarjeta = new TarViedma(111112, "pepe", 500.00, hoy.plusDays(30)); // $100 limit
                var mesa = new Mesa(1, libroDiario);
                Plato p1 = new Plato(null, null, 50.00),
                                p2 = new Plato("Pizza", "Marinara", 30.00),
                                p3 = new Plato("Hamburguesa", "Chimichurri", 40.00);

                Bebida b1 = new Bebida("Cerveza", 50), b2 = new Bebida("Vino", 70), b3 = new Bebida("Agua", 40);

                mesa.agregarBebida(b1);
                mesa.agregarBebida(b2);
                mesa.agregarBebida(b3);
                mesa.agregarBebida(b2);
                mesa.agregarPlato(p1);
                mesa.agregarPlato(p2);
                mesa.agregarPlato(p3);
                assertTrue((mesa.pagarMontoTotal(tarjeta, 0)) == 350);
        }

        @Test
        public void calculoPropina() {
                var libroDiario = new MokLibroVenta();
                LocalDate hoy = LocalDate.now();
                var tarjeta = new TarViedma(111112, "pepe", 500.00, hoy.plusDays(30)); // $100 limit
                var mesa = new Mesa(1, libroDiario);
                Plato p1 = new Plato(null, null, 50.00),
                                p2 = new Plato("Pizza", "Marinara", 30.00),
                                p3 = new Plato("Hamburguesa", "Chimichurri", 40.00);

                Bebida b1 = new Bebida("Cerveza", 50), b2 = new Bebida("Vino", 70), b3 = new Bebida("Agua", 40);

                mesa.agregarBebida(b1);
                mesa.agregarBebida(b2);
                mesa.agregarBebida(b3);
                mesa.agregarBebida(b2);
                mesa.agregarPlato(p1);
                mesa.agregarPlato(p2);
                mesa.agregarPlato(p3);
                mesa.pagarMontoTotal(tarjeta, 0);
                assertTrue(mesa.pagarPropina(tarjeta, 2) == 7);
        }

        @Test
        public void guardadoEnDisco() {
                var libroDiario = new EnDiscoLibroVentaDiario("/home/jose/objetos-2/Pruev de restoran.txt");
                LocalDate hoy = LocalDate.now();
                var tarjeta = new TarViedma(111112, "pepe", 500.00, hoy.plusDays(30)); // $100 limit
                var mesa = new Mesa(1, libroDiario);
                Plato p1 = new Plato(null, null, 50.00),
                                p2 = new Plato("Pizza", "Marinara", 30.00),
                                p3 = new Plato("Hamburguesa", "Chimichurri", 40.00);

                Bebida b1 = new Bebida("Cerveza", 50), b2 = new Bebida("Vino", 70), b3 = new Bebida("Agua", 40);

                mesa.agregarBebida(b1);
                mesa.agregarBebida(b2);
                mesa.agregarBebida(b3);
                mesa.agregarBebida(b2);
                mesa.agregarPlato(p1);
                mesa.agregarPlato(p2);
                mesa.agregarPlato(p3);
                mesa.pagarMontoTotal(tarjeta, 0);
                libroDiario.estaEstaVenta("350");
        }

        @Test
        public void guardadoEnBaseDeDatos() {
                var libroDiario = new EnBaseDatosLibroVentasDiario();
                LocalDate hoy = LocalDate.now();
                var tarjeta = new TarViedma(111112, "pepe", 500.00, hoy.plusDays(30)); // $100 limit
                var mesa = new Mesa(1, libroDiario);
                Plato p1 = new Plato(null, null, 50.00),
                                p2 = new Plato("Pizza", "Marinara", 30.00),
                                p3 = new Plato("Hamburguesa", "Chimichurri", 40.00);

                Bebida b1 = new Bebida("Cerveza", 50), b2 = new Bebida("Vino", 70), b3 = new Bebida("Agua", 40);

                mesa.agregarBebida(b1);
                mesa.agregarBebida(b2);
                mesa.agregarBebida(b3);
                mesa.agregarBebida(b2);
                mesa.agregarPlato(p1);
                mesa.agregarPlato(p2);
                mesa.agregarPlato(p3);
                mesa.pagarMontoTotal(tarjeta, 0);
                libroDiario.estaEstaVenta("350");
        }
}
