package com.svalero.transportesapi.controller;

import com.svalero.transportesapi.Service.EmpleadoService;
import com.svalero.transportesapi.exception.InvalidDataException;
import com.svalero.transportesapi.exception.ResourceNotFoundException;
import com.svalero.transportesapi.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerTodosEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        try {
            Empleado actualizado = empleadoService.actualizarEmpleado(id, empleado);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id) {
        try {
            empleadoService.eliminarEmpleado(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<Empleado>> obtenerEmpleadosConFiltros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String puesto,
            @RequestParam(required = false) Boolean activo) {

        List<Empleado> empleados = empleadoService.obtenerEmpleadosConFiltros(nombre, puesto, activo);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

}
