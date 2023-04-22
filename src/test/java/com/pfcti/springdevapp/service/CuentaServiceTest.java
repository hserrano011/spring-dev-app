package com.pfcti.springdevapp.service;

import com.pfcti.springdevapp.dto.CuentaDto;
import com.pfcti.springdevapp.model.Cuenta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CuentaServiceTest {

    @Autowired
    private CuentaService cuentaService;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void busquedaDinamicamentePorCriterios() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(true);
        List<CuentaDto> cuentaDtos = cuentaService.busquedaDinamicamentePorCriterios(cuentaDto);
        cuentaDtos.forEach(cuentaDto1 -> {
                    System.out.println("Cuenta resultado: " + cuentaDto1.getTipo() + "-" + cuentaDto1.getNumero());

                }
        );
        assertTrue(cuentaDtos.size() > 1);
    }
}