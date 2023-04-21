package com.pfcti.springdevapp.repository;

import com.pfcti.springdevapp.model.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InversionRepository extends JpaRepository<Inversion, Integer> {
    void deleteAllByClienteId(int clienteId);

}
