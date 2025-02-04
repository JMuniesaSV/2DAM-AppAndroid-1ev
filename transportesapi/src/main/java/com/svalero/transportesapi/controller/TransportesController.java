package com.svalero.transportesapi.controller;


import com.svalero.transportesapi.Service.TransporteService;
import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Transporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportes")
public class TransportesController {

    @Autowired
    private TransporteService transporteService;

    @GetMapping
    public ResponseEntity<List<Transporte>> obtenerTodosTransportes() {
        List<Transporte> transportes = transporteService.obtenerTodosTransportes();
        return new ResponseEntity<>(transportes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transporte> obtenerTransportePorId(@PathVariable Integer id) {
        try {
            Transporte transporte = transporteService.obtenerTransportePorId(id);
            return new ResponseEntity<>(transporte, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Transporte> crearTransporte(@RequestBody Transporte transporte) {
        try {
            Transporte nuevoTransporte = transporteService.crearTransporte(transporte);
            return new ResponseEntity<>(nuevoTransporte, HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Transporte> actualizarTransporte(@PathVariable Integer id, @RequestBody Transporte transporte) {
        try {
            Transporte actualizado = transporteService.actualizarTransporte(id, transporte);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransporte(@PathVariable Integer id) {
        try {
            transporteService.eliminarTransporte(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<Transporte>> obtenerTransportesConFiltros(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Boolean estado) {

        List<Transporte> transportes = transporteService.obtenerTransportesConFiltros(codigo, nombre, estado);
        return new ResponseEntity<>(transportes, HttpStatus.OK);
    }
}
