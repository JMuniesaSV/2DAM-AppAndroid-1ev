package com.svalero.transportesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String matricula;
    @Column
    private String marca;
    @Column
    private String modelo;
    @Column
    private Boolean disponible;
    @Column(name = "capacidad_carga")
    private Float capacidadCarga;
    @Column(name = "fecha_fabricacion")
    private LocalDate fechaFabricacion;

}
