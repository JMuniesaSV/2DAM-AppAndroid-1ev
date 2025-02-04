package com.svalero.transportesapi.repository;

import com.svalero.transportesapi.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RutaRepository extends CrudRepository<Ruta, Integer> {
    List<Ruta> findAll();
    Optional<Ruta> findById(int id);
    Ruta save(Ruta ruta);
    void deleteById(int id);
    boolean existsById(int id);

    List<Ruta> findByOrigenContainingIgnoreCase(String origen);
    List<Ruta> findByDestinoContainingIgnoreCase(String destino);
    List<Ruta> findByDistancia(Float distancia);
    List<Ruta> findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCase(String origen, String destino);
    List<Ruta> findByOrigenContainingIgnoreCaseAndDistancia(String origen, Float distancia);
    List<Ruta> findByDestinoContainingIgnoreCaseAndDistancia(String destino, Float distancia);
    List<Ruta> findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCaseAndDistancia(String origen, String destino, Float distancia);
}


