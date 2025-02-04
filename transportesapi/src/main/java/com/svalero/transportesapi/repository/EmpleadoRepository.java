package com.svalero.transportesapi.repository;

import com.svalero.transportesapi.model.Cliente;
import com.svalero.transportesapi.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {
    List<Empleado> findAll();
    Optional<Empleado> findById(int id);
    Empleado save(Empleado empleado);
    void deleteById(int id);
    List<Empleado> findByNombre(String nombre);
    boolean existsById(int id);

    List<Empleado> findByNombreIgnoreCase(String nombre);
    List<Empleado> findByPuestoIgnoreCase(String puesto);
    List<Empleado> findByActivo(Boolean activo);
    List<Empleado> findByNombreIgnoreCaseAndPuestoIgnoreCase(String nombre, String puesto);
    List<Empleado> findByNombreIgnoreCaseAndActivo(String nombre, Boolean activo);
    List<Empleado> findByPuestoIgnoreCaseAndActivo(String puesto, Boolean activo);
    List<Empleado> findByNombreIgnoreCaseAndPuestoIgnoreCaseAndActivo(String nombre, String puesto, Boolean activo);
}
