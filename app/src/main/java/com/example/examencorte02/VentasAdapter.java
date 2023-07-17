package com.example.examencorte02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examencorte02.Ventas;

import java.util.ArrayList;

public class VentasAdapter extends RecyclerView.Adapter<VentasAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<Ventas> listaVentas;
    private LayoutInflater inflater;
    private View.OnClickListener listener;

    public VentasAdapter(ArrayList<Ventas> listaVentas, Context context) {
        this.listaVentas = listaVentas;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ventas_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ventas venta = listaVentas.get(position);
        if (venta != null) {
            holder.txtCantidad.setText(String.valueOf(venta.getCantidad()));
            holder.txtPrecio.setText(String.valueOf(venta.getPrecio()));

            double total = venta.getCantidad() * venta.getPrecio();
            holder.txtTotal.setText(String.valueOf(total));
        }
    }

    @Override
    public int getItemCount() {
        return listaVentas.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCantidad;
        private TextView txtPrecio;
        private TextView txtTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }

    public void setVentaList(ArrayList<Ventas> listaVentas) {
        this.listaVentas.clear();
        this.listaVentas.addAll(listaVentas);
        notifyDataSetChanged();
    }

    public double calcularTotalVentas() {
        double total = 0;
        for (Ventas venta : listaVentas) {
            double subtotal = venta.getCantidad() * venta.getPrecio();
            total += subtotal;
        }
        return total;
    }
}
