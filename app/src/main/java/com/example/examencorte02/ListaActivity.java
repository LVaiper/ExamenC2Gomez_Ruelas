package com.example.examencorte02;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Modelo.VentasDb;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnRegresar;
    private Aplicacion app;
    private Ventas venta;
    private int posicion;
    private VentasDb ventasDb;
    private TextView lblTotalVenta;
    private VentasAdapter adaptadorVentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        this.inicializarComponentes();
        this.btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegresar();
            }
        });
        adaptadorVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView(view);
            }
        });
    }

    private void inicializarComponentes() {
        this.venta = new Ventas();
        this.posicion = -1;
        this.app = (Aplicacion) getApplication();
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView = findViewById(R.id.listaVentas);
        adaptadorVentas = app.getAdaptadorVentas();
        this.recyclerView.setAdapter(adaptadorVentas);
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.btnRegresar = findViewById(R.id.btnRegresar);
        this.lblTotalVenta = findViewById(R.id.lblTotalVenta);
        this.ventasDb = new VentasDb(getApplicationContext());

        // Calcular y mostrar el total de ventas
        double totalVenta = calcularTotalVenta();
        lblTotalVenta.setText("Total venta: " + totalVenta);
    }

    private void btnRegresar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que quieres regresar?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // Regresar al MainActivity
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void recyclerView(View view) {
        posicion = recyclerView.getChildAdapterPosition(view);
        venta = app.getVentas().get(posicion);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            app.getVentas().clear();
            app.getVentas().addAll(ventasDb.allVentas());
            adaptadorVentas.setVentaList(app.getVentas());
            adaptadorVentas.notifyDataSetChanged();

            // Calcular y mostrar el total de ventas
            double totalVenta = calcularTotalVenta();
            lblTotalVenta.setText("Total venta: " + totalVenta);
        }

        this.posicion = -1;
        this.venta = null;
    }

    private double calcularTotalVenta() {
        double total = 0;
        for (Ventas venta : app.getVentas()) {
            total += venta.getPrecio() * venta.getCantidad();
        }
        return total;
    }
}
