package com.cibertec.bancocibertec.persistence.entities;


import jakarta.persistence.*;
import com.cibertec.bancocibertec.persistence.enums.Enums.*;
import com.cibertec.bancocibertec.persistence.entities.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "cuenta")
public class Cuenta {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}")
@Column(unique = true, nullable = false)
private String numeroCuenta;


@Enumerated(EnumType.STRING)
private TipoCuenta tipoCuenta;

    @DecimalMin("0.00")
    private BigDecimal saldoActual;

    private LocalDateTime fechaApertura;

    private LocalDateTime fechaUltimoMovimiento;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimiento> movimientos;

    @PrePersist
    public void prePersist() {
        this.fechaApertura = LocalDateTime.now();
        this.fechaUltimoMovimiento = this.fechaApertura;
        if (this.estado == null) {
            this.estado = EstadoCuenta.ACTIVA;
        }
    }
}

/*
•id (Long): Identificador único
•numeroCuenta (String): Número de cuenta (único)
•tipoCuenta (Enum): AHORRO, CORRIENTE, PLAZO_FIJO
•saldoActual (BigDecimal): Saldo actual
•fechaApertura (LocalDateTime): Fecha de apertura
•fechaUltimoMovimiento (LocalDateTime): Fecha del último movimiento
•estado (Enum): ACTIVA, INACTIVA, CERRADA, BLOQUEADA
•cliente (Cliente): Cliente propietario
•movimientos (List<Movimiento>): Lista de movimientos


 */