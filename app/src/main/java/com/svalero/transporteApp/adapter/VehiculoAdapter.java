package com.svalero.transporteApp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TransporteApp.R;
import com.svalero.transporteApp.domain.Vehiculo;

import java.util.List;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.VehiculoHolder> {

    private List<Vehiculo> vehiculoList;

    public VehiculoAdapter(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    @NonNull
    @Override
    public VehiculoAdapter.VehiculoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehiculosview_item, parent, false);
        return new VehiculoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoAdapter.VehiculoHolder holder, int position) {
        holder.marca.setText(vehiculoList.get(position).getMarca());
        holder.modelo.setText(vehiculoList.get(position).getModelo());
    }

    @Override
    public int getItemCount() {
        return vehiculoList.size();
    }

    public class VehiculoHolder extends RecyclerView.ViewHolder {

        private TextView marca;
        private TextView modelo;

        public VehiculoHolder(@NonNull View itemView) {
            super(itemView);

            marca = itemView.findViewById(R.id.item_marca);
            modelo = itemView.findViewById(R.id.item_modelo);
        }

    }
}
