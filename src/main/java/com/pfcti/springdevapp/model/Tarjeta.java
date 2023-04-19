package com.pfcti.springdevapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tarjeta {
   @Id
    private int id;
   @Column
    private String numero;
   @Column
    private String tipo;

   @ManyToOne
    @JoinColumn (name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

}
