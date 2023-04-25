package com.pfcti.springdevapp.beans;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.ClienteQueryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sistemaExterno")
public class BuscadorClientesSistemaExterno implements BuscadorClientes {
    @Override
    public List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
        return new ArrayList<>();
    }
}
