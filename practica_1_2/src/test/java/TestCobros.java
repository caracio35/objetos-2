import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.Bebida;
import com.example.Mesa;
import com.example.Plato;
import com.example.TarMaster;
import com.example.TarVisa;

public class TestCobros {

    @Test
    public void calculoCostoVisa() {
        var tarjeta = new TarVisa(111112, "pepe", 500.00, LocalDate.now().plusDays(30)); // $100 limit
        var mesa = new Mesa(1);
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
        assertTrue((tarjeta.aplicarDescuento(mesa.calcularPrecioTotalBebidas(), mesa.calcularPrecioTotalPlatos(),
                0.97)) == 343.10);
    }

    @Test
    public void calculoCostoMaster() {
        var tarjeta = new TarMaster(111112, "pepe", 500.00, LocalDate.now().plusDays(30)); // $100 limit
        var mesa = new Mesa(1);
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
        assertTrue((tarjeta.aplicarDescuento(mesa.calcularPrecioTotalBebidas(), mesa.calcularPrecioTotalPlatos(),
                0.98)) == 347.6);
    }

}
