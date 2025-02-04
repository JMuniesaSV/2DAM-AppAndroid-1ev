package com.svalero.transportesapi.Service;

import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Ruta;
import com.svalero.transportesapi.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> obtenerTodasRutas() {
        try {
            List<Ruta> rutas = rutaRepository.findAll();
            if (rutas.isEmpty()) {
                throw new ResourceNotFoundException("No se han encontrado rutas");
            }
            return rutas;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las rutas", e);
        }
    }

    public Ruta obtenerRutaPorId(int id) {
        Optional<Ruta> ruta = rutaRepository.findById(id);
        if (ruta.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado la ruta con id " + id);
        }
        return ruta.get();
    }

    public Ruta crearRuta(Ruta ruta) {
        if (ruta == null || ruta.getDestino() == null) {
            throw new InvalidDataException("Datos invalidos para crear una ruta");
        }
        return rutaRepository.save(ruta);
    }

    public Ruta actualizarRuta(Integer id, Ruta ruta) {
        Optional<Ruta> rutaExistente = rutaRepository.findById(id);
        if (rutaExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado la ruta con id " + id);
        }
        Ruta rutaActualizada = rutaExistente.get();
        if (ruta.getOrigen() != null) {
            rutaActualizada.setOrigen(ruta.getOrigen());
        }
        if (ruta.getDestino() != null) {
            rutaActualizada.setDestino(ruta.getDestino());
        }
        if (ruta.getFechaSalida() != null) {
            rutaActualizada.setFechaSalida(ruta.getFechaSalida());
        }
        if (ruta.getDestino() != null) {
            rutaActualizada.setDestino(ruta.getDestino());
        }
        return rutaRepository.save(rutaActualizada);
    }

    public void eliminarRuta(Integer id) {
        if (!rutaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se ha encontrado la ruta con id " + id);
        }
        rutaRepository.deleteById(id);
    }

    public List<Ruta> obtenerRutasConFiltros(String origen, String destino, Float distancia) {
        if (origen != null && destino != null && distancia != null) {
            return rutaRepository.findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCaseAndDistancia(origen, destino, distancia);
        } else if (origen != null && destino != null) {
            return rutaRepository.findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCase(origen, destino);
        } else if (origen != null && distancia != null) {
            return rutaRepository.findByOrigenContainingIgnoreCaseAndDistancia(origen, distancia);
        } else if (destino != null && distancia != null) {
            return rutaRepository.findByDestinoContainingIgnoreCaseAndDistancia(destino, distancia);
        } else if (origen != null) {
            return rutaRepository.findByOrigenContainingIgnoreCase(origen);
        } else if (destino != null) {
            return rutaRepository.findByDestinoContainingIgnoreCase(destino);
        } else if (distancia != null) {
            return rutaRepository.findByDistancia(distancia);
        } else {
            return rutaRepository.findAll();
        }
    }

}


