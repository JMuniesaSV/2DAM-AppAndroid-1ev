package com.svalero.transporteApp.contract;

import com.svalero.transporteApp.domain.Vehiculo;

public interface RegisterVehiculoContract {

    interface Model {
        interface OnRegisterVehiculoListener {
            void OnRegisterVehiculoSuccess(Vehiculo registeredVehiculo);

            void OnRegisterVehiculoError(String message);
        }

        void registerVehiculo(Vehiculo vehiculo, OnRegisterVehiculoListener listener);
    }

    interface View {
        void showErrorMessages(String message);
        void showSourcesMessage(String message);
    }

    interface Presenter {
        void registerVehiculo(Vehiculo vehiculo);
    }

}
