package com.pfcti.springdevapp.repository;

import com.pfcti.springdevapp.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuentaRepository extends JpaRepository <Cuenta, Integer>, JpaSpecificationExecutor<Cuenta> {
    void deleteAllByClienteId(int clienteId);

   /* @Modifying
    @Query(value = "update Cuenta c set c.estado =:false where  c.numero =:numero")
    void updateCuentaEstadoByNumeroQuery(String numero);*/
    List<Cuenta> findCuentaByCliente_Id(int idCliente );

    void findCuentaByNumero (String numero);

}
