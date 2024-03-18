package com.example;

import java.util.ArrayList;

public class Mesa {
    private int numMesa;
    private ArrayList<Plato> platos = new ArrayList(30); // Crear um array de 30 posicioes para los produtos de la mesa
    private ArrayList<Bebida> bebidas = new ArrayList<>();

    public Mesa(int numMesa) {
        this.numMesa = numMesa;
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

}
