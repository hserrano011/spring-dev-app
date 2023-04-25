package com.pfcti.springdevapp.beans;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.ClienteQueryDto;
import com.pfcti.springdevapp.dto.enums.ClienteQueryType;
import com.pfcti.springdevapp.model.Cliente;
import com.pfcti.springdevapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("baseDeDatos")
public class BuscadorClientesBdd  implements BuscadorClientes {

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
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
