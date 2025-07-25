package com.cibertec.bancocibertec.services;

import com.cibertec.bancocibertec.DTO.AperturaCuentaForm;
import com.cibertec.bancocibertec.persistence.repositories.*;
import com.cibertec.bancocibertec.persistence.entities.*;
import com.cibertec.bancocibertec.persistence.enums.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final ClienteRepository clienteRepo;
    private final CuentaRepository cuentaRepo;

    @Transactional
    public Cuenta abrirCuenta(AperturaCuentaForm form) {
        Cliente cliente = clienteRepo.findById(form.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if (!cliente.getEstado().equals(Enums.EstadoCliente.ACTIVO)) {
            throw new RuntimeException("El cliente no está activo");
        }

        long cuentasActivas = cuentaRepo.countByClienteAndEstado(cliente, Enums.EstadoCuenta.ACTIVA);
        if (cuentasActivas >= 3) {
            throw new RuntimeException("El cliente ya tiene 3 cuentas activas");
        }

        BigDecimal saldoMinimo = switch (form.getTipoCuenta()) {
            case AHORRO -> BigDecimal.valueOf(10);
            case CORRIENTE -> BigDecimal.valueOf(1000);
            case PLAZO_FIJO -> BigDecimal.valueOf(5000);
        };

        if (form.getSaldoInicial().compareTo(saldoMinimo) < 0) {
            throw new RuntimeException("Saldo inicial no cumple el mínimo para tipo " + form.getTipoCuenta());
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(generarNumeroCuenta());
        cuenta.setTipoCuenta(form.getTipoCuenta());
        cuenta.setSaldoActual(form.getSaldoInicial());
        cuenta.setCliente(cliente);
        cuenta.setEstado(Enums.EstadoCuenta.ACTIVA);
        cuenta.setFechaApertura(LocalDateTime.now());
        cuenta.setFechaUltimoMovimiento(LocalDateTime.now());

        return cuentaRepo.save(cuenta);
    }

    private String generarNumeroCuenta() {
        Random r = new Random();
        return String.format("%04d-%04d-%04d", r.nextInt(10000), r.nextInt(10000), r.nextInt(10000));
    }
}
