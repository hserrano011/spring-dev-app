package com.pfcti.springdevapp.service;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.model.Cliente;
import com.pfcti.springdevapp.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;
    private DireccionRepository direccionRepository;
    private CuentaRepository cuentaRepository;
    private TarjetaRepository tarjetaRepository;
    private InversionRepository inversionRepository;

    public void insertarCliente (ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save(cliente);

    }

    public ClienteDto obtenerCliente (int clienteId) {
        Cliente cliente =
                clienteRepository.findById(clienteId).orElseThrow(()->
                {throw new RuntimeException("No existe");});
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNombre (cliente.getNombre());
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setTelefono(cliente.getTelefono());
        return clienteDto;
    }

    public void  actualizarCliente (ClienteDto clienteDto) {
        Cliente cliente =
                clienteRepository.findById(clienteDto.getId()).orElseThrow(()->
                {throw new RuntimeException("No existe");});
        cliente.setId((clienteDto.getId()));
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(cliente.getTelefono());
        clienteRepository.save(cliente);
    }
 /*
    public List<Cliente> obtenerClientes() {
            return clienteRepository.findAll();
        }

  */
    public List<ClienteDto> obtenerClientes() {
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAll();
        clientes.forEach(cliente -> {
            clienteDtos.add(fromClienteToClienteDto(cliente));
        });
        return clienteDtos;
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


    public List<ClienteDto> obtenerClientesPorCodigoISOPaisYCuentasActivas (String codigoISOPais) {
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente>clientes = clienteRepository.findClientesByPaisAndCuentas_EstadoIsTrue(codigoISOPais);
        clientes.forEach((cliente -> {
            clienteDtos.add(fromClienteToClienteDto(cliente));
        }));
        return clienteDtos;
    }

    public void eliminarCliente (int clientId){
        direccionRepository.deleteAllByCliente_Id(clientId);
        cuentaRepository.deleteAllByClienteId(clientId);
        tarjetaRepository.deleteAllByClienteId(clientId);
        inversionRepository.deleteAllByClienteId(clientId);
        clienteRepository.deleteById(clientId);
    }

    public List<ClienteDto> buscarPorApellidos(String apellidos){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.buscarPorApellidos(apellidos);
        clientes.forEach((cliente -> clienteDtos.add(fromClienteToClienteDto(cliente))));
        return clienteDtos;
    }

    public List<ClienteDto> buscarPorApellidosQueryNativo(String apellidos){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Tuple> tuples = clienteRepository.buscarPorApellidosQueryNativo(apellidos);
        tuples.forEach (tuple -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setApellidos((String) tuple.get("apellidos"));
            clienteDto.setNombre((String) tuple.get("nombre"));
            clienteDto.setCedula((String)tuple.get("cedula"));
            clienteDto.setTelefono((String) tuple.get("telefono"));
            clienteDto.setId((Integer) tuple.get("id"));
            clienteDtos.add(clienteDto);
        });
        return clienteDtos;
    }

    public void  updateClienteByQuery(String nombre, String apellidos) {
        clienteRepository.updateClienteByQuery(nombre, apellidos);
    }

    public List<ClienteDto> findByApellidosAndAndNombre(String apellidos, String nombre){
        return clienteRepository
                .findByApellidosAndAndNombre(apellidos,nombre)
                .stream()
                .map(this::fromClienteToClienteDto)
                .collect(Collectors.toList());
    }


    public List<ClienteDto> obtenerClientesExtranjerosYTarjetasInactivas(String codigoISOPais) {
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente>clientes = clienteRepository.findClienteByPaisIsNotContainingIgnoreCaseAndAndTarjetas_estadoIsFalse(codigoISOPais);
        clientes.forEach((cliente -> {
            clienteDtos.add(fromClienteToClienteDto(cliente));
        }));
        return clienteDtos;
    }


}

