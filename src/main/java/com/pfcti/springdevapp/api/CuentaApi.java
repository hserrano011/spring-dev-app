package com.pfcti.springdevapp.api;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.CuentaDto;
import com.pfcti.springdevapp.service.ClienteService;
import com.pfcti.springdevapp.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/cuenta")
public class CuentaApi {

    @Autowired
    private CuentaService cuentaService;


    @GetMapping("/{id}")
    public List<CuentaDto> buscarCuentas(@PathVariable int id){
        log.info("Busqueda de cuenta : {}", id);
        return cuentaService.buscarCuentas(id); }

    @PostMapping
    public void guardarCuenta(@Valid  @RequestBody CuentaDto cuentaDto, int clienteId){
        log.info("Cuenta de cliente : {}", cuentaDto);
        cuentaService.insertarCuenta(cuentaDto, clienteId); }

    @PutMapping
    public void desactivarCuenta(@RequestBody  CuentaDto cuentaDto){
        log.info("Cuenta de cliente : {}", cuentaDto);
        cuentaService.desactivarCuenta(cuentaDto); }

}
