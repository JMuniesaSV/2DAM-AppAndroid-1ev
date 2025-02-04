package com.svalero.transporteApp.presenter;

import com.svalero.transporteApp.contract.VehiculoListContract;
import com.svalero.transporteApp.domain.Vehiculo;
import com.svalero.transporteApp.model.VehiculoListModel;

import java.util.List;

public class VehiculoListPresenter implements VehiculoListContract.Presenter, VehiculoListContract.Model.OnLoadVehiculosListener {

    private VehiculoListContract.View view;
    private VehiculoListContract.Model model;

    public VehiculoListPresenter(VehiculoListContract.View view) {
        this.view = view;
        model = new VehiculoListModel();
    }

    @Override
    public void loadVehiculos() {
        model.loadVehiculos(this);
    }

    @Override
    public void OnLoadVehiculosSuccess(List<Vehiculo> vehiculoList) {
        view.listVehiculos(vehiculoList);
    }

    @Override
    public void OnLoadVehiculosError(String message) {
        view.showErrorMessages(message);
    }
}
