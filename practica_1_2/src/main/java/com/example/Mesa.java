package com.example;

import java.util.ArrayList;

public class Mesa {
    private LibroVentaDiario libroVenta;
    private int numMesa;
    private ArrayList<Plato> platos;// Crear um array de 30 posicioes para los produtos de la mesa
    private ArrayList<Bebida> bebidas;
    @SuppressWarnings("unused")
    private Tarjeta tarjeta;
    @SuppressWarnings("unused")
    private Double monto;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Mesa(int numMesa, LibroVentaDiario libroVenta) {
        this.numMesa = numMesa;
        platos = new ArrayList(30);
        bebidas = new ArrayList<>(30);
        monto = 0.0;
        this.libroVenta = libroVenta;

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

    public double pagarMontoTotal(Tarjeta t, double Propina) {

        double totalBebidas = calcularPrecioTotalBebidas();
        double totalPlatos = calcularPrecioTotalPlatos();
        // pagarPropina(totalPlatos, totalBebidas,Propina);
        double montoPagado = t.cobrar(totalBebidas, totalPlatos);
        libroVenta.agregarVenta(String.valueOf(montoPagado));
        return montoPagado;

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

    public double pagarPropina(Tarjeta t, double Propina) {

        double porsentajePropina = Propina / 100;
        double tBebidas = calcularPrecioTotalBebidas();
        double tPlatos = calcularPrecioTotalPlatos();
        tBebidas = tBebidas * porsentajePropina;
        tPlatos = tPlatos * porsentajePropina;
        return t.cobrar(tBebidas, tPlatos);
    }

}
