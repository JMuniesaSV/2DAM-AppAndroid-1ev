package com.svalero.transportesapi.controller;

import com.svalero.transportesapi.Service.VehiculoService;
import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerTodosVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerTodosVehiculos();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        return new ResponseEntity<>(vehiculo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo nuevoVehiculo = vehiculoService.crearVehiculo(vehiculo);
            return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo actualizado = vehiculoService.actualizarVehiculo(id, vehiculo);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Integer id) {
        try {
            vehiculoService.eliminarVehiculo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<Vehiculo>> obtenerVehiculosConFiltros(
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo) {

        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosConFiltros(matricula, marca, modelo);
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }
}