package com.example;

public class Plato {
    private String nombre;
    private String descripccion;
    private Double precioUnitario;

    public Plato(String nombre, String descrpcion, Double precio) {
        this.nombre = nombre;
        this.descripccion = descrpcion;
        this.precioUnitario = precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripccion() {
        return this.descripccion;
    }

    public Double getPrecio() {
        return this.precioUnitario;
    }
}
