package Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.examencorte02.Ventas;

import java.util.ArrayList;

public class VentasDb implements Persistencia, Proyeccion {

    private Context context;
    private VentasDbHelper helper;
    private SQLiteDatabase db;

    public VentasDb(Context context, VentasDbHelper helper) {
        this.context = context;
        this.helper = helper;
    }

    public VentasDb(Context context) {
        this.context = context;
        this.helper = new VentasDbHelper(this.context);
    }

    @Override
    public void openDataBase() {
        db = helper.getWritableDatabase();
    }

    @Override
    public void closeDataBase() {
        helper.close();
    }

    @Override
    public long insertVentas(Ventas venta) {
        ContentValues values = new ContentValues();

        values.put(DefineTable.Ventas.COLUMN_NAME_NUM_BOMBA, venta.getNumBomba());
        values.put(DefineTable.Ventas.COLUMN_NAME_TIPO_GASOLINA, venta.getTipoGasolina());
        values.put(DefineTable.Ventas.COLUMN_NAME_PRECIO, venta.getPrecio());
        values.put(DefineTable.Ventas.COLUMN_NAME_CANTIDAD, venta.getCantidad());

        this.openDataBase();
        long num = db.insert(DefineTable.Ventas.TABLE_NAME, null, values);
        this.closeDataBase();
        Log.d("agregar", "insertVenta: " + num);

        return num;    }



    public Ventas getVenta(int id) {
        db = helper.getWritableDatabase();

        Cursor cursor = db.query(
                DefineTable.Ventas.TABLE_NAME,
                DefineTable.Ventas.REGISTRO,
                DefineTable.Ventas.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Ventas venta = readVentas(cursor);
            cursor.close();
            return venta;
        }
        return null;
    }



    @Override
    public Ventas readVentas(Cursor cursor) {
        Ventas venta = new Ventas();
        venta.setId(cursor.getInt(0));
        venta.setNumBomba(cursor.getInt(1));
        venta.setTipoGasolina(cursor.getInt(2));
        venta.setPrecio(cursor.getDouble(3));
        venta.setCantidad(cursor.getInt(4));
        return venta;
    }

    public ArrayList<Ventas> allVentas() {
        this.openDataBase();

        Cursor cursor = db.query(
                DefineTable.Ventas.TABLE_NAME,
                DefineTable.Ventas.REGISTRO,
                null, null, null, null, null);

        ArrayList<Ventas> ventas = new ArrayList<>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Ventas venta = readVentas(cursor);
            ventas.add(venta);
            cursor.moveToNext();
        }

        cursor.close();

        this.closeDataBase();
        return ventas;
    }
}
