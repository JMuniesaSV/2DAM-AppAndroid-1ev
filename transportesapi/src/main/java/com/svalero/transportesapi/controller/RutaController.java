package com.svalero.transportesapi.controller;

import com.svalero.transportesapi.Service.RutaService;
import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Ruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    @Autowired
    private RutaService rutaService;


    @GetMapping
    public ResponseEntity<List<Ruta>> obtenerTodasRutas() {
        List<Ruta> rutas = rutaService.obtenerTodasRutas();
        return new ResponseEntity<>(rutas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Ruta> obtenerRutaPorId(@PathVariable Integer id) {
        Ruta ruta = rutaService.obtenerRutaPorId(id);
        return new ResponseEntity<>(ruta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ruta> crearRuta(@RequestBody Ruta ruta) {
        try {
            Ruta nuevaRuta = rutaService.crearRuta(ruta);
            return new ResponseEntity<>(nuevaRuta, HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Ruta> actualizarRuta(@PathVariable Integer id, @RequestBody Ruta ruta) {
        try {
            Ruta actualizada = rutaService.actualizarRuta(id, ruta);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRuta(@PathVariable Integer id) {
        try {
            rutaService.eliminarRuta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/filtros")
    public ResponseEntity<List<Ruta>> obtenerRutasConFiltros(
            @RequestParam(required = false) String origen,
            @RequestParam(required = false) String destino,
            @RequestParam(required = false) Float distancia) {

        List<Ruta> rutas = rutaService.obtenerRutasConFiltros(origen, destino, distancia);
        return new ResponseEntity<>(rutas, HttpStatus.OK);
    }
}