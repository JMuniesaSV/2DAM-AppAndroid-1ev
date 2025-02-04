package com.svalero.transportesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;
    @Column
    private String telefono;
    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;
    @Column
    private Boolean activo;
    @Column
    private String puesto;

}
