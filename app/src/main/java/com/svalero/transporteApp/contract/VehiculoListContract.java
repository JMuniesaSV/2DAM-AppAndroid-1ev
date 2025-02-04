package com.svalero.transporteApp.contract;

import com.svalero.transporteApp.domain.Vehiculo;

import java.util.List;

public interface VehiculoListContract {

    interface Model {
        interface OnLoadVehiculosListener {
            void OnLoadVehiculosSuccess(List<Vehiculo> vehiculoList);
            void OnLoadVehiculosError(String message);
        }
        void loadVehiculos(OnLoadVehiculosListener listener);
    }

    interface View {
        void listVehiculos(List<Vehiculo> vehiculoList);
        void showErrorMessages(String message);
        void showSourcesMessage(String message);
    }

    interface Presenter {
        void loadVehiculos();
    }
}
