package com.example;

import java.time.LocalDate;

public class TarMaster extends Tarjeta {

    public TarMaster(int numTarjeta, String nombrePropetorio, double limiteCredito, LocalDate fechaVencimiento) {
        super(numTarjeta, nombrePropetorio, limiteCredito, fechaVencimiento);
        this.descuento = 0.98;
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
    public double cobrar(double montoBebidas, double montoPlato) {

        double monto = this.aplicarDescuento(montoBebidas, montoPlato);

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
