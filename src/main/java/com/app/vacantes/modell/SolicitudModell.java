package com.app.vacantes.modell;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity

//Anotaciones loombook
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitudes")
public class SolicitudModell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idsolicitud;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "curriculum")
    private String curriculum;

    @Column(name = "comentarios")
    private String comentarios;
}
