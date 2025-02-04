package com.svalero.transportesapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column
    private String telefono;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
    @Column
    private Boolean vip;

}
