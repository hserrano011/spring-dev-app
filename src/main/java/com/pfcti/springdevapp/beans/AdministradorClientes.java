package com.pfcti.springdevapp.beans;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.ClienteQueryDto;
import com.pfcti.springdevapp.dto.enums.ClienteQueryType;
import com.pfcti.springdevapp.model.Cliente;
import com.pfcti.springdevapp.repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AdministradorClientes {

    private ClienteRepository clienteRepository;
    private ClienteQueryType defaultClienteQueryType;

    public AdministradorClientes(ClienteRepository clienteRepository, ClienteQueryType defaultClienteQueryType) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> AdministradorClientes INSTANCIA: " + this);
        this.clienteRepository = clienteRepository;
        this.defaultClienteQueryType = defaultClienteQueryType;

    }


    public List<ClienteDto> obtenerListaClientesPorCriterio(ClienteQueryDto clienteQueryDto) {
        List<Cliente> clientes = null;
        if (ClienteQueryType.CEDULA.equals(clienteQueryDto.getClienteQueryType())){
            clientes = clienteRepository.findClientesByCedula(clienteQueryDto.getTextoBusqueda());
        }else if (ClienteQueryType.NOMBRES.equals((clienteQueryDto.getClienteQueryType()))) {
            clientes = clienteRepository.findClientesByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(clienteQueryDto.getTextoBusqueda(),
                    clienteQueryDto.getTextoBusqueda());
        }
        return clientes.stream()
                .map(this::fromClienteToClienteDto)
                .collect(Collectors.toList());

       // return null;

    }
    private ClienteDto fromClienteToClienteDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setTelefono(cliente.getTelefono());
        return clienteDto;
    }
}
