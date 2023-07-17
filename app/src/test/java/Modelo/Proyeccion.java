package Modelo;

import android.database.Cursor;

import com.example.examencorte02.Ventas;

import java.util.ArrayList;

public interface Proyeccion {
    public Ventas getVenta(int id);
    public ArrayList<Ventas> allVentas();
    public Ventas readVentas(Cursor cursor);
}
