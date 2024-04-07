package com.example;

public class MokLibroVenta implements LibroVentaDiario {
    String monto;

    @Override
    public void agregarVenta(String monto) {
        this.monto = monto;
    }

    @Override
    public boolean estaEstaVenta(String monto1) {
        return monto.equals(monto1);
    }

}
