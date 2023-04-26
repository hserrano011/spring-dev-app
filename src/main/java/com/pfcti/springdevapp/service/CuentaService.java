package com.pfcti.springdevapp.service;

import com.pfcti.springdevapp.criteria.CuentaSpecification;
import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.CuentaDto;
import com.pfcti.springdevapp.model.Cliente;
import com.pfcti.springdevapp.model.Cuenta;
import com.pfcti.springdevapp.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;


    private CuentaDto fromCuentaToCuentaDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }

    public List<CuentaDto> busquedaDinamicamentePorCriterios(CuentaDto cuentaDtoFilter){
        return cuentaRepository.findAll(cuentaSpecification.buildFilter(cuentaDtoFilter))
                .stream()
                .map(this::fromCuentaToCuentaDto)
                .collect(Collectors.toList());
    }


    public void insertarCuenta (CuentaDto cuentaDto, int clienteId){
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cuenta.setCliente(cliente);
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setNumero(cuentaDto.getNumero());
        cuenta.setEstado(cuentaDto.getEstado());
        cuentaRepository.save(cuenta);

    }

    public void desactivarCuenta(CuentaDto cuentaDto){
        Cuenta cuenta =
                cuentaRepository.findById(cuentaDto.getId()).orElseThrow(()->
                {throw new RuntimeException("No existe");});
        cuenta.setId((cuentaDto.getId()));
        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);
    }

    public List<CuentaDto>buscarCuentas (int id){
        List<CuentaDto> cuentaDtos = new ArrayList<>();
        List<Cuenta> cuentas =  cuentaRepository.findCuentaByCliente_Id(id);
        cuentas.forEach(cuenta -> { cuentaDtos.add(fromCuentaToCuentaDto(cuenta)); });
    return cuentaDtos;
    }

}





