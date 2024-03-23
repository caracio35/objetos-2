package com.example;

import java.util.ArrayList;

public class Mesa {
    private int numMesa;
    private ArrayList<Plato> platos;// Crear um array de 30 posicioes para los produtos de la mesa
    private ArrayList<Bebida> bebidas;
    private Tarjeta tarjeta;
    private Double monto;

    public Mesa(int numMesa) {
        this.numMesa = numMesa;
        platos = new ArrayList(30);
        bebidas = new ArrayList<>(30);
        monto = 0.0;

    }

    public void agregarPlato(Plato p) {
        platos.add(p);
    }

    public boolean removerPlato(Plato p) {
        return platos.remove(p);
    }

    public void mostrarMenu() {
        System.out.println("Numero de mesa: " + numMesa);
        for (Plato plato : platos) {
            System.out.print("\n" + plato);
        }
    }

    public void agregarBebida(Bebida b) {
        bebidas.add(b);
    }

    public double pagarMontoTotal(Tarjeta t) {

        double totalBebidas = calcularPrecioTotalBebidas();
        double totalPlatos = calcularPrecioTotalPlatos();

        return t.cobrar(totalBebidas, totalPlatos);

    }

    public double calcularPrecioTotalBebidas() {
        double precioTotal = 0;
        for (Bebida bebida : bebidas) {
            precioTotal += bebida.calcularPrecio();
        }
        return precioTotal;
    }

    public double calcularPrecioTotalPlatos() {
        double precio = 0;
        for (Plato p : platos) {
            precio += p.calcularPrecio();
        }
        return precio;
    }

}
