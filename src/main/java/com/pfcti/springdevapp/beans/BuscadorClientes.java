package com.pfcti.springdevapp.beans;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.ClienteQueryDto;

import java.util.List;

public interface BuscadorClientes {
    List<ClienteDto> obtenerListaClientes (ClienteQueryDto clienteQueryDto);
}
