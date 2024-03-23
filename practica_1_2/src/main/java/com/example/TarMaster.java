package com.example;

import java.time.LocalDate;

public class TarMaster extends Tarjeta {
    Double descuento = 0.97;

    public TarMaster(int numTarjeta, String nombrePropetorio, double limiteCredito, LocalDate fechaVencimiento,
            double descuento) {
        super(numTarjeta, nombrePropetorio, limiteCredito, fechaVencimiento, descuento);
        // TODO Auto-generated constructor stub
    }

    @Override
    double aplicarDescuento(double preciobebida, double precioPlato) {
        return (preciobebida + precioPlato * descuento);
    }

    @Override
    boolean estaActiva() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isAfter(fechaVencimiento);
    }

    @Override
    public String cobrar(double monto) {
        if (this.estaActiva()) {
            if (limiteCredito >= monto) {
                limiteCredito -= monto;
                return "Se ha realizado el pago de $" + monto + "muchas gracias por tu compra ";
            } else {
                return ("No tiene suficiente dinero en su tarjeta.");

            }
        } else {
            return "La tarjeta no está activa";
        }
    }

}
