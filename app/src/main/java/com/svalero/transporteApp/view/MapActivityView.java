package com.svalero.transporteApp.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.TransporteApp.R;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.transporteApp.domain.Vehiculo;
import com.svalero.transporteApp.util.MapUtil;

import java.util.ArrayList;

public class MapActivityView extends AppCompatActivity implements Style.OnStyleLoaded {

    private ArrayList<Vehiculo> vehiculoList;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        vehiculoList = intent.getParcelableArrayListExtra("vehiculos");

        mapView = findViewById(R.id.mapView);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mapType = preferences.getString("preference_map_type", "Calles");
        if (mapType.equals("Calles")) {
            mapType = Style.MAPBOX_STREETS;
        } else {
            mapType = Style.SATELLITE;
        }
        mapView.getMapboxMap().loadStyleUri(mapType, this);
        pointAnnotationManager = MapUtil.initializePointAnnotationManager(mapView);
    }

    private void viewVehiculos() {
        for (Vehiculo vehiculo : vehiculoList) {
            addMarker(vehiculo.getMatricula(), vehiculo.getLatitud(), vehiculo.getLongitud());
        }
    }

    private void addMarker(String message, double lat, double lon) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.red_marker))
                .withTextField(message)
                .withPoint(Point.fromLngLat(lon, lat));
        pointAnnotationManager.create(marker);
    }


    @Override
    public void onStyleLoaded(Style style) {

    }
}
