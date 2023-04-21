package com.pfcti.springdevapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDto {
    private int id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private List<DireccionDto> direcciones;
    private String pais;
    private List<CuentaDto> cuentas;
    private List<TarjetaDto> tarjetas;

}
