package com.cibertec.bancocibertec.persistence.repositories;
import com.cibertec.bancocibertec.persistence.entities.Cliente;
import com.cibertec.bancocibertec.persistence.entities.Cuenta;
import com.cibertec.bancocibertec.persistence.enums.Enums.EstadoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    long countByClienteAndEstado(Cliente cliente, EstadoCuenta estado);
    List<Cuenta> findByCliente(Cliente cliente);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}



