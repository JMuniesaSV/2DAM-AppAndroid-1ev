package com.svalero.transporteApp.presenter;

import com.svalero.transporteApp.contract.RegisterVehiculoContract;
import com.svalero.transporteApp.domain.Vehiculo;
import com.svalero.transporteApp.model.RegisterVehiculoModel;

public class RegisterVehiculoPresenter implements RegisterVehiculoContract.Presenter, RegisterVehiculoContract.Model.OnRegisterVehiculoListener {

    private RegisterVehiculoContract.View view;
    private RegisterVehiculoContract.Model model;

    public RegisterVehiculoPresenter(RegisterVehiculoContract.View view) {
        this.view = view;
        model = new RegisterVehiculoModel();
    }

    @Override
    public void registerVehiculo(Vehiculo vehiculo) {
      if (vehiculo.getMatricula().isEmpty() || vehiculo.getMarca().isEmpty() || vehiculo.getModelo().isEmpty()){
          view.showErrorMessages("Debe rellenar todos los campos vehiculo, matricula, marca y modelo");
          return;
      }
        model.registerVehiculo(vehiculo, this);
    }

    @Override
    public void OnRegisterVehiculoSuccess(Vehiculo registeredVehiculo) {
        view.showSourcesMessage("Vehiculo registrado correctamente");
    }

    @Override
    public void OnRegisterVehiculoError(String message) {
        view.showErrorMessages(message);
    }

}
