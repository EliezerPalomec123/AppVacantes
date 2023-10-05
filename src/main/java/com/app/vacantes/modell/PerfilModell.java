package com.app.vacantes.modell;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

//Anotaciones loombook
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfiles")
public class PerfilModell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idperfil;

    @Column(name = "nombre")
    private String nombre;
}
