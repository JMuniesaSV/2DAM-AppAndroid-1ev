package com.svalero.transporteApp.api;

import com.svalero.transporteApp.domain.Vehiculo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VehiculosApiInterface {

    @GET("api/vehiculos")
    Call<List<Vehiculo>> getVehiculos();

    @GET("api/vehiculos/{id}")
    Call<Vehiculo> getVehiculo(@Path("id") int id);

    @POST("api/vehiculos")
    Call<Vehiculo> addVehiculo(@Path("id") int id, @Body Vehiculo vehiculo);
}
