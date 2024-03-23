package com.example;

import java.time.LocalDate;

public abstract class Tarjeta {

    int numTarjeta;
    String nombrePropietario;
    double limiteCredito;
    LocalDate fechaVencimiento;
    Double descuento;

    public Tarjeta(int numTarjeta, String nombrePropetorio, double limiteCredito, LocalDate fechaVencimiento) {
        this.numTarjeta = numTarjeta;
        this.nombrePropietario = nombrePropetorio;
        this.limiteCredito = limiteCredito;
        this.fechaVencimiento = fechaVencimiento;
    }

    abstract double aplicarDescuento(double preciobebida, double precioPlato);

    abstract boolean estaActiva();

    public abstract double cobrar(double totalBebidas, double totalPlatos);

}
