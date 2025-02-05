package com.svalero.transporteApp.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.svalero.transporteApp.api.VehiculosApi;
import com.svalero.transporteApp.api.VehiculosApiInterface;
import com.svalero.transporteApp.contract.RegisterVehiculoContract;
import com.svalero.transporteApp.domain.Vehiculo;

public class RegisterVehiculoModel implements RegisterVehiculoContract.Model {

    @Override
    public void registerVehiculo(Vehiculo vehiculo, OnRegisterVehiculoListener listener) {
        VehiculosApiInterface vehiculosApi = VehiculosApi.buildInstance();
        Call<Vehiculo> callRegisterVehiculo = vehiculosApi.addVehiculo(1, vehiculo);
        callRegisterVehiculo.enqueue(new Callback<Vehiculo>() {
            @Override
            public void onResponse(Call<Vehiculo> call, Response<Vehiculo> response) {
                switch (response.code()) {
                    case 201:
                        listener.OnRegisterVehiculoSuccess(response.body());
                        break;
                    case 400:
                        listener.OnRegisterVehiculoError("Error validando petición " + response.message());
                        break;
                    case 500:
                        listener.OnRegisterVehiculoError("Error interno de la API " + response.message());
                        break;
                    default:
                        listener.OnRegisterVehiculoError("Error invocando la API " + response.message());
                        break;
                }
            }

            @Override
            public void onFailure(Call<Vehiculo> call, Throwable t) {
                listener.OnRegisterVehiculoError("No se ha podido conectar inténtelo de nuevo");
            }
        });


    }
}
