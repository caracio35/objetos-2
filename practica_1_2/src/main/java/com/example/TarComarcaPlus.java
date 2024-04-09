package com.example;

import java.time.LocalDate;

public class TarComarcaPlus extends Tarjeta {
    public TarComarcaPlus(int numTarjeta, String nombrePropetorio, double limiteCredito, LocalDate fechaVencimiento) {
        super(numTarjeta, nombrePropetorio, limiteCredito, fechaVencimiento);
        this.descuento = 0.98;
    }

    boolean estaActiva() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isAfter(fechaVencimiento);
    }

    @Override
    double aplicarDescuento(double precioBebida, double precioPlato) {
        return (precioBebida * descuento + precioPlato * descuento);
    }

    @Override
    public double cobrar(double totalBebidas, double totalPlatos) {
        double monto = this.aplicarDescuento(totalBebidas, totalPlatos);

        if (this.estaActiva()) {
            if (limiteCredito >= monto) {
                limiteCredito -= monto;
                return monto;
            } else {
                throw new RuntimeException("no tiene saldo suficiente ");

            }
        } else {
            throw new RuntimeException("no esta activa  ");
        }
    }
}
