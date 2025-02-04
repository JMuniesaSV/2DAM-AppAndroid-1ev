package com.svalero.transportesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String origen;
    @Column
    private String destino;
    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;
    @Column
    private Float distancia;

}
