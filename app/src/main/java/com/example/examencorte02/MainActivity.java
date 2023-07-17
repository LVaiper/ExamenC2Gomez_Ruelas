package com.example.examencorte02;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import Modelo.VentasDb;

public class MainActivity extends AppCompatActivity {

    private TextView lblInicioBomba;
    private EditText txtNumBomba;
    private RadioButton rbRegular;
    private RadioButton rbExtra;
    private EditText txtPrecio;
    private EditText txtCapacidad;
    private EditText txtContador;
    private Button btnIniciarBomba;
    private EditText txtCantidad;
    private Button btnRealizarVenta;
    private TextView lblCanPreTo;
    private TextView lblResultado;
    private Button btnRealizarVenta2;
    private Button btnConsultar;
    private Button btnLimpiar;
    private Button btnSalir;

    private Aplicacion app;
    private Ventas venta;
    private VentasDb ventasDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (Aplicacion) getApplication();
        ventasDb = new VentasDb(getApplicationContext());

        lblInicioBomba = findViewById(R.id.lblInicioBomba);
        txtNumBomba = findViewById(R.id.txtNumBomba);
        rbRegular = findViewById(R.id.rbRegular);
        rbExtra = findViewById(R.id.rbExtra);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCapacidad = findViewById(R.id.txtCapacidad);
        txtContador = findViewById(R.id.txtContador);
        btnIniciarBomba = findViewById(R.id.btnIniciarBomba);
        txtCantidad = findViewById(R.id.txtCantidad);
        btnRealizarVenta = findViewById(R.id.btnRealizarVenta);
        lblCanPreTo = findViewById(R.id.lblCanPreTo);
        lblResultado = findViewById(R.id.lblResultado);
        btnRealizarVenta2 = findViewById(R.id.btnRealizarVenta2);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnSalir = findViewById(R.id.btnSalir);

        btnIniciarBomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarBomba();
            }
        });

        btnRealizarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarVenta();
            }
        });

        btnRealizarVenta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarVenta();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirListaVentas();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirApp();
            }
        });
    }

    private void iniciarBomba() {
        String numBomba = txtNumBomba.getText().toString().trim();
        String precio = txtPrecio.getText().toString().trim();
        String capacidad = txtCapacidad.getText().toString().trim();
        String contador = txtContador.getText().toString().trim();

        if (TextUtils.isEmpty(numBomba) || TextUtils.isEmpty(precio) || TextUtils.isEmpty(capacidad) || TextUtils.isEmpty(contador)) {
            Toast.makeText(MainActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int tipoGasolina = rbRegular.isChecked() ? 0 : 1;
        double precioLitro = Double.parseDouble(precio);
        int capacidadBomba = Integer.parseInt(capacidad);
        int contadorLitros = Integer.parseInt(contador);

        venta = new Ventas();
        venta.setNumBomba(Integer.parseInt(numBomba));
        venta.setTipoGasolina(tipoGasolina);
        venta.setPrecio(precioLitro);
        venta.setCantidad(0);

        lblInicioBomba.setText("Bomba " + numBomba + " iniciada");

        btnIniciarBomba.setEnabled(false);
        txtNumBomba.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtCapacidad.setEnabled(false);
        txtContador.setEnabled(false);
        rbRegular.setEnabled(false);
        rbExtra.setEnabled(false);
    }

    private void realizarVenta() {
        if (venta == null) {
            Toast.makeText(MainActivity.this, "Por favor, inicie la bomba primero", Toast.LENGTH_SHORT).show();
            return;
        }

        String cantidadStr = txtCantidad.getText().toString().trim();

        if (TextUtils.isEmpty(cantidadStr)) {
            Toast.makeText(MainActivity.this, "Por favor, ingrese la cantidad a vender", Toast.LENGTH_SHORT).show();
            return;
        }

        int cantidad = Integer.parseInt(cantidadStr);

        if (cantidad <= 0) {
            Toast.makeText(MainActivity.this, "La cantidad debe ser mayor a cero", Toast.LENGTH_SHORT).show();
            return;
        }

        int contadorLitros = Integer.parseInt(txtContador.getText().toString().trim());

        if (cantidad > contadorLitros) {
            Toast.makeText(MainActivity.this, "La cantidad de litros solicitada es mayor al contador de litros", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalPagar = venta.getPrecio() * cantidad;

        lblResultado.setText(cantidad + " x " + venta.getPrecio() + " = " + totalPagar);
        venta.setCantidad(cantidad);


    }




    private void abrirListaVentas() {
        Intent intent = new Intent(MainActivity.this, ListaActivity.class);
        startActivity(intent);
    }

    private void limpiarCampos() {
        txtNumBomba.setText("");
        txtPrecio.setText("");
        txtCapacidad.setText("");
        txtContador.setText("");
        txtCantidad.setText("");
        lblInicioBomba.setText("Iniciando bomba");

        btnIniciarBomba.setEnabled(true);
        txtNumBomba.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtCapacidad.setEnabled(true);
        txtContador.setEnabled(true);
        rbRegular.setEnabled(true);
        rbExtra.setEnabled(true);

        venta = null;
        lblCanPreTo.setText("Cantidad   Precio  Total a Pagar");
        lblResultado.setText("");
    }

    private void salirApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que quieres salir?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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

    private void registrarVenta() {
        if (venta == null) {
            Toast.makeText(MainActivity.this, "Por favor, inicie la bomba primero", Toast.LENGTH_SHORT).show();
            return;
        }

        String cantidadStr = txtCantidad.getText().toString().trim();

        if (TextUtils.isEmpty(cantidadStr)) {
            Toast.makeText(MainActivity.this, "Por favor, ingrese la cantidad a vender", Toast.LENGTH_SHORT).show();
            return;
        }

        int cantidad = Integer.parseInt(cantidadStr);
        int contadorLitros = Integer.parseInt(txtContador.getText().toString().trim());

        if (cantidad <= 0) {
            Toast.makeText(MainActivity.this, "La cantidad debe ser mayor a cero", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cantidad > contadorLitros) {
            Toast.makeText(MainActivity.this, "La cantidad de litros solicitada es mayor al contador de litros", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalPagar = venta.getPrecio() * cantidad;

        Ventas nuevaVenta = new Ventas();
        nuevaVenta.setNumBomba(venta.getNumBomba());
        nuevaVenta.setTipoGasolina(venta.getTipoGasolina());
        nuevaVenta.setPrecio(venta.getPrecio());
        nuevaVenta.setCantidad(cantidad);

        long resultado = ventasDb.insertVentas(nuevaVenta);

        if (resultado != -1) {
            Toast.makeText(MainActivity.this, "Venta realizada correctamente", Toast.LENGTH_SHORT).show();
            lblCanPreTo.setText("Cantidad   Precio  Total a Pagar");
            lblResultado.setText("");
            txtCantidad.setText("");

            contadorLitros -= cantidad;
            txtContador.setText(String.valueOf(contadorLitros));
        } else {
            Toast.makeText(MainActivity.this, "No se pudo realizar la venta", Toast.LENGTH_SHORT).show();
        }
    }



}
