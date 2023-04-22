package com.pfcti.springdevapp.repository;

import com.pfcti.springdevapp.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CuentaRepository extends JpaRepository <Cuenta, Integer>, JpaSpecificationExecutor<Cuenta> {
    void deleteAllByClienteId(int clienteId);
}
