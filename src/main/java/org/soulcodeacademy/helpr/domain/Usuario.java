package org.soulcodeacademy.helpr.domain;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios") // coloca um noe especifico na tabela
public class Usuario {
    @Id // torna o campo abaixouma PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Preenche o campo id com auto_INCREMENT
    protected Integer id;
}
