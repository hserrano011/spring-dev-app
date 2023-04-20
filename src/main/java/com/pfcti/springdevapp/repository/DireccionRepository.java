package com.pfcti.springdevapp.repository;

import com.pfcti.springdevapp.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
}
