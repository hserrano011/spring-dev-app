package com.pfcti.springdevapp.dto;

import com.pfcti.springdevapp.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CuentaDto {
    private int id;
    private String numero;
    private String tipo;
    private int clienteId;
    private boolean estado;
}
