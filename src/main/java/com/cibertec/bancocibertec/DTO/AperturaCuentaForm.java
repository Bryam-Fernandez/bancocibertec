package com.cibertec.bancocibertec.DTO;

import com.cibertec.bancocibertec.persistence.enums.Enums.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AperturaCuentaForm {
    @NotNull
    private Long clienteId;

    @NotNull
    private TipoCuenta tipoCuenta;

    @DecimalMin("0.00")
    private BigDecimal saldoInicial;


}
