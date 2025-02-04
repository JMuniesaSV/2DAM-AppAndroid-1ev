package com.svalero.transportesapi.Service;

import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Transporte;
import com.svalero.transportesapi.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public List<Transporte> obtenerTodosTransportes() {
        try {
            List<Transporte> transportes = transporteRepository.findAll();
            if (transportes.isEmpty()) {
                throw new ResourceNotFoundException("No se han encontrado transportes");
            }
            return transportes;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los transportes", e);
        }
    }

    public Transporte obtenerTransportePorId(int id) {
        Optional<Transporte> transporte = transporteRepository.findById(id);
        if (transporte.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el transporte con id " + id);
        }
        return transporte.get();
    }

    public Transporte crearTransporte(Transporte transporte) {
        if (transporte == null || transporte.getCodigo() == null) {
            throw new InvalidDataException("Datos invalidos para crear un transporte");
        }
        return transporteRepository.save(transporte);
    }

    public Transporte actualizarTransporte(Integer id, Transporte transporte) {
        Optional<Transporte> transporteExistente = transporteRepository.findById(id);
        if (transporteExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el transporte con id " + id);
        }
        Transporte transporteActualizado = transporteExistente.get();
        if (transporte.getCodigo() != null) {
            transporteActualizado.setCodigo(transporte.getCodigo());
        }
        if (transporte.getNombre() != null) {
            transporteActualizado.setNombre(transporte.getNombre());
        }
        if (transporte.getFechaCreacion() != null) {
            transporteActualizado.setFechaCreacion(transporte.getFechaCreacion());
        }
        if (transporte.getCliente() != null) {
            transporteActualizado.setCliente(transporte.getCliente());
        }
        if (transporte.getRuta() != null) {
            transporteActualizado.setRuta(transporte.getRuta());
        }
        if (transporte.getEstado() != null) {
            transporteActualizado.setEstado(transporte.getEstado());
        }
        return transporteRepository.save(transporteActualizado);
    }

    public void eliminarTransporte(Integer id) {
        if (!transporteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se ha encontrado el transporte con id " + id);
        }
        transporteRepository.deleteById(id);
    }

    public List<Transporte> obtenerTransportesConFiltros(String codigo, String nombre, Boolean estado) {
        if (codigo != null && nombre != null && estado != null) {
            return transporteRepository.findByCodigoIgnoreCaseAndNombreIgnoreCaseAndEstado(codigo, nombre, estado);
        } else if (codigo != null && nombre != null) {
            return transporteRepository.findByCodigoIgnoreCaseAndNombreIgnoreCase(codigo, nombre);
        } else if (codigo != null && estado != null) {
            return transporteRepository.findByCodigoIgnoreCaseAndEstado(codigo, estado);
        } else if (nombre != null && estado != null) {
            return transporteRepository.findByNombreIgnoreCaseAndEstado(nombre, estado);
        } else if (codigo != null) {
            return transporteRepository.findByCodigoIgnoreCase(codigo);
        } else if (nombre != null) {
            return transporteRepository.findByNombreIgnoreCase(nombre);
        } else if (estado != null) {
            return transporteRepository.findByEstado(estado);
        } else {
            return transporteRepository.findAll();
        }
    }

}
