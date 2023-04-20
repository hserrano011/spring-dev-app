package com.pfcti.springdevapp.dto;

import com.pfcti.springdevapp.model.Cliente;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DireccionDto {
    private int id;
    private String direccion;
    private String nomenclatura;
    private int clienteId;
}
