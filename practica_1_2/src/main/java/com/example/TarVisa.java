package com.example;

import java.time.LocalDate;

public class TarVisa extends Tarjeta {

    public TarVisa(int numTarjeta, String nombrePropetorio, double limiteCredito, LocalDate fechaVencimiento) {
        super(numTarjeta, nombrePropetorio, limiteCredito, fechaVencimiento);
        this.descuento = 0.97;
    }

    public boolean estaActiva() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isAfter(fechaVencimiento);
    }

    @Override
    double aplicarDescuento(double precioBebida, double precioPlato) {
        return (precioBebida * descuento + precioPlato);
    }

    @Override
    public double cobrar(double totalBebidas, double totalPlatos) {
        double monto = this.aplicarDescuento(totalBebidas, totalPlatos);

        if (this.estaActiva()) {
            if (limiteCredito >= monto) {
                limiteCredito -= monto;
                return monto;
            } else {
                return -1;

            }
        } else {
            return -1;
        }

    }

}
