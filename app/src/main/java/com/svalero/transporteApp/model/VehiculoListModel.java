package com.svalero.transporteApp.model;


import com.svalero.transporteApp.api.VehiculosApi;
import com.svalero.transporteApp.api.VehiculosApiInterface;
import com.svalero.transporteApp.contract.VehiculoListContract;
import com.svalero.transporteApp.domain.Vehiculo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class VehiculoListModel implements VehiculoListContract.Model {

    public VehiculoListModel() {
    }

    @Override
    public void loadVehiculos(OnLoadVehiculosListener listener) {

        VehiculosApiInterface vehiculosApi = VehiculosApi.buildInstance();
        Call<List<Vehiculo>> getVehiculosCall = vehiculosApi.getVehiculos();
        getVehiculosCall.enqueue(new Callback<List<Vehiculo>>() {

            @Override
            public void onResponse(Call<List<Vehiculo>> call, Response<List<Vehiculo>> response) {
                if (response.code() == 200) {
                    listener.OnLoadVehiculosSuccess(response.body());
                } else if (response.code() == 500) {
                    listener.OnLoadVehiculosError("API no disponible");
                } else {
                    listener.OnLoadVehiculosError(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Vehiculo>> call, Throwable t) {
                listener.OnLoadVehiculosError("No se ha podido conectar" + "Inténtelo de nuevo");
            }
        });

    }
}
