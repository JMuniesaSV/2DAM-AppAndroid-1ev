package com.svalero.transportesapi.Service;

import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Cliente;
import com.svalero.transportesapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosClientes() {
        try {
            List<Cliente> cliente = clienteRepository.findAll();
            if (cliente.isEmpty()) {
                throw new ResourceNotFoundException("No se han encontrado clientes");
            }
            return cliente;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los clientes", e);
        }
    }

    public Cliente obtenerClientePorId(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el cliente con id " + id);
        }
        return cliente.get();
    }

    public Cliente crearCliente(Cliente cliente) {
        if (cliente == null || cliente.getNombre() == null) {
            throw new InvalidDataException("Datos invalidos para crear un cliente");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se ha encontrado el cliente con id " + id);
        }
        Cliente clienteActualizado = clienteExistente.get();
        if (cliente.getNombre() != null) {
            clienteActualizado.setNombre(cliente.getNombre());
        }
        if (cliente.getDireccion() != null) {
            clienteActualizado.setDireccion(cliente.getDireccion());
        }
        if (cliente.getTelefono() != null) {
            clienteActualizado.setTelefono(cliente.getTelefono());
        }
        if (cliente.getFechaRegistro() != null) {
            clienteActualizado.setFechaRegistro(cliente.getFechaRegistro());
        }
        if (cliente.getVip() != null) {
            clienteActualizado.setVip(cliente.getVip());
        }
        return clienteRepository.save(clienteActualizado);
    }



    public void eliminarCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se ha encontrado el cliente con id " + id);
        }
        clienteRepository.deleteById(id);
    }

    public List<Cliente> obtenerClientesConFiltros(String nombre, String direccion, Boolean vip) {
        try {
            if (nombre != null && direccion != null && vip != null) {
                return clienteRepository.findByNombreContainingIgnoreCaseAndDireccionContainingIgnoreCaseAndVip(nombre, direccion, vip);
            } else if (nombre != null) {
                return clienteRepository.findByNombreContainingIgnoreCase(nombre);
            } else if (direccion != null) {
                return clienteRepository.findByDireccionContainingIgnoreCase(direccion);
            } else if (vip != null) {
                return clienteRepository.findByVip(vip);
            }

            List<Cliente> clientes = clienteRepository.findAll();
            if (clientes.isEmpty()) {
                throw new ResourceNotFoundException("No se han encontrado clientes");
            }
            return clientes;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los clientes", e);
        }
    }

}
