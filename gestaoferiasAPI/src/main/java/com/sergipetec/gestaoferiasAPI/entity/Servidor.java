package com.sergipetec.gestaoferiasAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "servidor")
@Getter
@Setter
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String matricula;

    @OneToMany(mappedBy = "servidor")
    private List<PeriodoFerias> periodosFerias;
}
