package com.svalero.transportesapi.Service;

import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Vehiculo;
import com.svalero.transportesapi.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> obtenerTodosVehiculos() {
        try {
            List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            if (vehiculos.isEmpty()) {
                throw new ResourceNotFoundException("No se han encontrado vehiculos");
            }
            return vehiculos;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los vehiculos", e);
        }
    }

    public Vehiculo obtenerVehiculoPorId(int id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el vehiculo con id " + id);
        }
        return vehiculo.get();
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getMatricula() == null) {
            throw new InvalidDataException("Datos invalidos para crear un vehículo");
        }
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo actualizarVehiculo(Integer id, Vehiculo vehiculo) {

        Optional<Vehiculo> vehiculoExistente = vehiculoRepository.findById(id);
        if (vehiculoExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el vehículo con id " + id);
        }

        Vehiculo vehiculoActualizar = vehiculoExistente.get();

        if (vehiculo.getMatricula() != null) {
            vehiculoActualizar.setMatricula(vehiculo.getMatricula());
        }
        if (vehiculo.getMarca() != null) {
            vehiculoActualizar.setMarca(vehiculo.getMarca());
        }
        if (vehiculo.getModelo() != null) {
            vehiculoActualizar.setModelo(vehiculo.getModelo());
        }
        if (vehiculo.getDisponible() != null) {
            vehiculoActualizar.setDisponible(vehiculo.getDisponible());
        }
        if (vehiculo.getCapacidadCarga() != null) {
            vehiculoActualizar.setCapacidadCarga(vehiculo.getCapacidadCarga());
        }
        if (vehiculo.getFechaFabricacion() != null) {
            vehiculoActualizar.setFechaFabricacion(vehiculo.getFechaFabricacion());
        }

        return vehiculoRepository.save(vehiculoActualizar);
    }

    public void eliminarVehiculo(Integer id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se ha encontrado el vehículo con id " + id);
        }
        vehiculoRepository.deleteById(id);
    }

    public List<Vehiculo> obtenerVehiculosConFiltros(String matricula, String marca, String modelo) {
        if (matricula != null && marca != null && modelo != null) {
            return vehiculoRepository.findByMatriculaIgnoreCaseAndMarcaIgnoreCaseAndModeloIgnoreCase(matricula, marca, modelo);
        } else if (matricula != null && marca != null) {
            return vehiculoRepository.findByMatriculaIgnoreCaseAndMarcaIgnoreCase(matricula, marca);
        } else if (matricula != null && modelo != null) {
            return vehiculoRepository.findByMatriculaIgnoreCaseAndModeloIgnoreCase(matricula, modelo);
        } else if (marca != null && modelo != null) {
            return vehiculoRepository.findByMarcaIgnoreCaseAndModeloIgnoreCase(marca, modelo);
        } else if (matricula != null) {
            return vehiculoRepository.findByMatriculaIgnoreCase(matricula);
        } else if (marca != null) {
            return vehiculoRepository.findByMarcaIgnoreCase(marca);
        } else if (modelo != null) {
            return vehiculoRepository.findByModeloIgnoreCase(modelo);
        } else {
            return vehiculoRepository.findAll();
        }
    }

}
