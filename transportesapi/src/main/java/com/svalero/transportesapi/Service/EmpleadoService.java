package com.svalero.transportesapi.Service;

import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Cliente;
import com.svalero.transportesapi.model.Empleado;
import com.svalero.transportesapi.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodosEmpleados() {
        try {
            List<Empleado> empleados = empleadoRepository.findAll();
            if (empleados.isEmpty()) {
                throw new ResourceNotFoundException("No se han encontrado empleados");
            }
            return empleados;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los empleados", e);
        }
    }

    public Empleado obtenerEmpleadoPorId(int id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el empleado con id " + id);
        }
        return empleado.get();
    }

    public Empleado crearEmpleado(Empleado empleado) {
        if (empleado == null || empleado.getNombre() == null) {
            throw new InvalidDataException("Datos invalidos para crear un empleado");
        }
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizarEmpleado(Integer id, Empleado empleado) {
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(id);
        if (empleadoExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el empleado con id " + id);
        }
        Empleado empleadoActualizado = empleadoExistente.get();
        if (empleado.getNombre() != null) {
            empleadoActualizado.setNombre(empleado.getNombre());
        }
        if (empleado.getTelefono() != null) {
            empleadoActualizado.setTelefono(empleado.getTelefono());
        }
        if (empleado.getFechaContratacion() != null) {
            empleadoActualizado.setFechaContratacion(empleado.getFechaContratacion());
        }
        if (empleado.getActivo() != null) {
            empleadoActualizado.setActivo(empleado.getActivo());
        }
        if (empleado.getPuesto() != null) {
            empleadoActualizado.setPuesto(empleado.getPuesto());
        }
        return empleadoRepository.save(empleadoActualizado);
    }

    public void eliminarEmpleado(Integer id) {
        if (!empleadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se ha encontrado el empleado con id " + id);
        }
        empleadoRepository.deleteById(id);
    }

    public List<Empleado> obtenerEmpleadosConFiltros(String nombre, String puesto, Boolean activo) {
        if (nombre != null && puesto != null && activo != null) {
            return empleadoRepository.findByNombreIgnoreCaseAndPuestoIgnoreCaseAndActivo(nombre, puesto, activo);
        } else if (nombre != null && puesto != null) {
            return empleadoRepository.findByNombreIgnoreCaseAndPuestoIgnoreCase(nombre, puesto);
        } else if (nombre != null && activo != null) {
            return empleadoRepository.findByNombreIgnoreCaseAndActivo(nombre, activo);
        } else if (puesto != null && activo != null) {
            return empleadoRepository.findByPuestoIgnoreCaseAndActivo(puesto, activo);
        } else if (nombre != null) {
            return empleadoRepository.findByNombreIgnoreCase(nombre);
        } else if (puesto != null) {
            return empleadoRepository.findByPuestoIgnoreCase(puesto);
        } else if (activo != null) {
            return empleadoRepository.findByActivo(activo);
        } else {
            return empleadoRepository.findAll();
        }
    }
}
