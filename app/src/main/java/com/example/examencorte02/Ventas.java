package com.example.examencorte02;

public class Ventas {
    private int id;
    private int numBomba;
    private int tipoGasolina;
    private double precio;
    private int cantidad;


    public Ventas()
    {
        this.id=0;
        this.numBomba=0;
        this.tipoGasolina=0;
        this.precio=0.0;
        this.cantidad=0;
    }

    public Ventas(int id, int numBomba, int tipoGasolina, double precio, int cantidad) {
        this.id = id;
        this.numBomba = numBomba;
        this.tipoGasolina = tipoGasolina;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumBomba() {
        return numBomba;
    }

    public void setNumBomba(int numBomba) {
        this.numBomba = numBomba;
    }

    public int getTipoGasolina() {
        return tipoGasolina;
    }

    public void setTipoGasolina(int tipoGasolina) {
        this.tipoGasolina = tipoGasolina;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
