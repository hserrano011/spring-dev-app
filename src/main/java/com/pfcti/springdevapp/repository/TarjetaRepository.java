package com.pfcti.springdevapp.repository;

import com.pfcti.springdevapp.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
}
