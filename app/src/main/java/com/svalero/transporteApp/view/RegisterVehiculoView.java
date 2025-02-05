package com.svalero.transporteApp.view;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TransporteApp.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.transporteApp.contract.RegisterVehiculoContract;
import com.svalero.transporteApp.domain.Vehiculo;
import com.svalero.transporteApp.presenter.RegisterVehiculoPresenter;
import com.svalero.transporteApp.util.MapUtil;

import java.text.ParseException;


public class RegisterVehiculoView extends AppCompatActivity implements RegisterVehiculoContract.View, Style.OnStyleLoaded, OnMapClickListener {

    private RegisterVehiculoPresenter presenter;
    private MapView mapView;
    private AnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehiculo);

        presenter = new RegisterVehiculoPresenter(this);
        initializedMapView();
        pointAnnotationManager = MapUtil.initializePointAnnotationManager(mapView);
        initializeGesturesPlugin();
    }

    public void register(View view) {
        if (currentPoint == null) {
            Toast.makeText(this, "Selecciona una ubicación en el mapa", Toast.LENGTH_SHORT).show();
            return;
        }
        String marca = ((EditText) findViewById(R.id.marca)).getText().toString();
        String modelo = ((EditText) findViewById(R.id.modelo)).getText().toString();

        Vehiculo vehiculo = new Vehiculo(marca, modelo, currentPoint.latitude(), currentPoint.longitude());
        presenter.registerVehiculo(vehiculo);
    }

    @Override
    public void showErrorMessages(String message) {
        Snackbar.make(findViewById(R.id.add_vehiculo_button), message, BaseTransientBottomBar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showSourcesMessage(String message) {
        Snackbar.make(findViewById(R.id.add_vehiculo_button), message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }


     private void initializedMapView() {
        mapView = findViewById(R.id.registerMapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
     }

    private void initializeGesturesPlugin() {
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    private void addMarker(double latitude, double longitude) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.create(marker);
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude());
        return true;
    }

    @Override
    public void onStyleLoaded(Style style) {

    }

}
