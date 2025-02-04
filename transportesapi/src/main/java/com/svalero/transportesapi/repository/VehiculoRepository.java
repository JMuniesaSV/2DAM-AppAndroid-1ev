package com.svalero.transportesapi.repository;

import com.svalero.transportesapi.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {
    List<Vehiculo> findAll();
    Optional<Vehiculo> findById(int id);
    Vehiculo save(Vehiculo vehiculo);
    void deleteById(int id);
    boolean existsById(int id);

    List<Vehiculo> findByMatriculaIgnoreCase(String matricula);
    List<Vehiculo> findByMarcaIgnoreCase(String marca);
    List<Vehiculo> findByModeloIgnoreCase(String modelo);
    List<Vehiculo> findByMatriculaIgnoreCaseAndMarcaIgnoreCase(String matricula, String marca);
    List<Vehiculo> findByMatriculaIgnoreCaseAndModeloIgnoreCase(String matricula, String modelo);
    List<Vehiculo> findByMarcaIgnoreCaseAndModeloIgnoreCase(String marca, String modelo);
    List<Vehiculo> findByMatriculaIgnoreCaseAndMarcaIgnoreCaseAndModeloIgnoreCase(String matricula, String marca, String modelo);
}