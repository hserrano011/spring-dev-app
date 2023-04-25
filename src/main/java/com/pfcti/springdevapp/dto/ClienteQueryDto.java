package com.pfcti.springdevapp.dto;

import com.pfcti.springdevapp.dto.enums.ClienteQueryType;
import lombok.Data;


@Data
public
class ClienteQueryDto {
    private String textoBusqueda;
    private ClienteQueryType clienteQueryType;
}
