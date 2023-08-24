package com.app.vacantes.modell;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity

//Anotaciones loombook
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="vacantes")
public class VacanteModell {

    //Identificador de la tabla vacantes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idvacante;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "salario")
    private double salario;

    @Column(name = "status")
    private String status;

    @Column(name = "destacado")
    private String destacado;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "detalles")
    private String detalles;

    @ManyToOne
    private CategoriaModell categoria;
}
