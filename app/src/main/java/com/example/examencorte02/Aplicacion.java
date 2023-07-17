package com.example.examencorte02;

import android.app.Application;
import android.util.Log;

import com.example.examencorte02.Ventas;
import com.example.examencorte02.VentasAdapter;

import java.util.ArrayList;

import Modelo.VentasDb;

public class Aplicacion extends Application {

    public static ArrayList<Ventas> ventas;
    private VentasAdapter adaptadorVentas;
    private VentasDb ventasDb;

    public ArrayList<Ventas> getVentas() {
        return ventas;
    }

    public VentasAdapter getAdaptadorVentas() {
        return adaptadorVentas;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ventasDb = new VentasDb(getApplicationContext());
        ventas = ventasDb.allVentas();
        ventasDb.openDataBase();

        Log.d("", "onCreate: Tamaño ArrayList: " + ventas.size());
        this.adaptadorVentas = new VentasAdapter(ventas, this);
    }

    public void agregarVenta(Ventas venta) {
        ventasDb.openDataBase();
        long resultado = ventasDb.insertVentas(venta);
        if (resultado != -1) {
            venta.setId((int) resultado);
            ventas.add(venta);
            adaptadorVentas.notifyDataSetChanged();
        }
    }
}
