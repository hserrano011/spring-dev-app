package com.pfcti.springdevapp.repository;

import com.pfcti.springdevapp.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository <Cuenta, Integer> {
    void deleteAllByClienteId(int clienteId);
}
