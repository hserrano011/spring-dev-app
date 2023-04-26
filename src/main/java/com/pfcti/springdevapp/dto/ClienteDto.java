package com.pfcti.springdevapp.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
public class ClienteDto {
    private int id;
    @NotNull
    @Size(max = 10, message = "El nombre debe contener max 10 caracteres")
    private String nombre;
    private String apellidos;

    private String cedula;
    private String telefono;
    private List<DireccionDto> direcciones;
    private String pais;
    private List<CuentaDto> cuentas;
    private List<TarjetaDto> tarjetas;

}
