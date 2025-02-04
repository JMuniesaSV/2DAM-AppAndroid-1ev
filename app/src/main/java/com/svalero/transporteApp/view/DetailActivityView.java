package com.svalero.transporteApp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TransporteApp.R;

public class DetailActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        printVehiculo(id);
    }

    private void printVehiculo(int id) {
        ((TextView) findViewById(R.id.vehiculo_id)).setText(String.valueOf(id));
    }
}
