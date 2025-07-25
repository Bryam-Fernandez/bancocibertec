package com.cibertec.bancocibertec.persistence.repositories;

import com.cibertec.bancocibertec.persistence.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
