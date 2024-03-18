package com.example;

public class Bebida {
    private String nomeBebida;
    private double precoUnitario;

    public Bebida(String n, double p) {
        this.nomeBebida = n;
        this.precoUnitario = p;
    }

    public String getNome() {
        return this.nomeBebida;
    }

    public Double getPrecioUnitario() {
        return this.precoUnitario;
    }
}
