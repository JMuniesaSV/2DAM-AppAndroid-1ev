package com.svalero.transportesapi.repository;

import com.svalero.transportesapi.model.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransporteRepository extends CrudRepository<Transporte, Integer> {
    List<Transporte> findAll();
    Optional<Transporte> findById(int id);
    Transporte save(Transporte transporte);
    void deleteById(int id);
    boolean existsById(int id);

    List<Transporte> findByCodigoIgnoreCase(String codigo);
    List<Transporte> findByNombreIgnoreCase(String nombre);
    List<Transporte> findByEstado(Boolean estado);
    List<Transporte> findByCodigoIgnoreCaseAndNombreIgnoreCase(String codigo, String nombre);
    List<Transporte> findByCodigoIgnoreCaseAndEstado(String codigo, Boolean estado);
    List<Transporte> findByNombreIgnoreCaseAndEstado(String nombre, Boolean estado);
    List<Transporte> findByCodigoIgnoreCaseAndNombreIgnoreCaseAndEstado(String codigo, String nombre, Boolean estado);
}
