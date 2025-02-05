package com.svalero.transporteApp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TransporteApp.R;
import com.svalero.transporteApp.adapter.VehiculoAdapter;
import com.svalero.transporteApp.contract.VehiculoListContract;
import com.svalero.transporteApp.domain.Vehiculo;
import com.svalero.transporteApp.presenter.VehiculoListPresenter;

import java.util.ArrayList;
import java.util.List;

public class VehiculoListView extends AppCompatActivity implements VehiculoListContract.View {

    private VehiculoAdapter vehiculoAdapter;
    private ArrayList<Vehiculo> vehiculoList;
    private VehiculoListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new VehiculoListPresenter(this);

        vehiculoList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.vehiculos_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vehiculoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vehiculoList.clear();
        presenter.loadVehiculos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_map) {
            Intent intent = new Intent(this, MapActivityView.class);
            intent.putParcelableArrayListExtra("vehiculoList", vehiculoList);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_register_vehiculo) {
            Intent intent = new Intent(this, RegisterVehiculoView.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_preferences) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void registerVehiculo(View view) {
        Intent intent = new Intent(this, RegisterVehiculoView.class);
        startActivity(intent);
    }

    @Override
    public void listVehiculos(List<Vehiculo> vehiculos) {
        vehiculoList.addAll(vehiculos);
        vehiculoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessages(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSourcesMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
