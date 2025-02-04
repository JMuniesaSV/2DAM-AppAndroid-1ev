package com.svalero.transportesapi.repository;

import com.svalero.transportesapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    List<Cliente> findAll();
    Optional<Cliente> findById(int id);
    boolean existsById(int id);
    Cliente save(Cliente cliente);
    void deleteById(int id);

    List<Cliente> findByNombreContainingIgnoreCaseAndDireccionContainingIgnoreCaseAndVip(@Param("nombre") String nombre,
                                                                                         @Param("direccion") String direccion,
                                                                                         @Param("vip") Boolean vip);

    List<Cliente> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
    List<Cliente> findByDireccionContainingIgnoreCase(@Param("direccion") String direccion);
    List<Cliente> findByVip(@Param("vip") Boolean vip);
}
