package com.pfcti.springdevapp.repository;

import com.pfcti.springdevapp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
}
