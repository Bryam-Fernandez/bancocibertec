package com.cibertec.bancocibertec.persistence.entities;

import com.cibertec.bancocibertec.persistence.enums.Enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "movimientos")
public class Movimiento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_transaccion", unique = true, nullable = false, length = 50)
    @NotBlank(message = "El número de transacción es obligatorio")
    private String numeroTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    @NotNull(message = "El tipo de movimiento es obligatorio")
    private TipoMovimiento tipoMovimiento;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @Column(name = "monto_anterior", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoAnterior;

    @Column(name = "monto_nuevo", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoNuevo;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "fecha_transaccion", nullable = false)
    private LocalDateTime fechaTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "canal", nullable = false)
    @NotNull(message = "El canal es obligatorio")
    private Canal canal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    @NotNull(message = "La cuenta es obligatoria")
    private Cuenta cuenta;

    @Column(name = "cuenta_destino", length = 20)
    private String cuentaDestino;
    
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getMontoAnterior() {
		return montoAnterior;
	}

	public void setMontoAnterior(BigDecimal montoAnterior) {
		this.montoAnterior = montoAnterior;
	}

	public BigDecimal getMontoNuevo() {
		return montoNuevo;
	}

	public void setMontoNuevo(BigDecimal montoNuevo) {
		this.montoNuevo = montoNuevo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	// Constructor por defecto
    public Movimiento() {
        this.fechaTransaccion = LocalDateTime.now();
    }

    // Constructor con parámetros básicos
    public Movimiento(String numeroTransaccion, TipoMovimiento tipoMovimiento, BigDecimal monto,
                      BigDecimal montoAnterior, BigDecimal montoNuevo, Canal canal, Cuenta cuenta) {
        this();
        this.numeroTransaccion = numeroTransaccion;
        this.tipoMovimiento = tipoMovimiento;
        this.monto = monto;
        this.montoAnterior = montoAnterior;
        this.montoNuevo = montoNuevo;
        this.canal = canal;
        this.cuenta = cuenta;
    }

    @PrePersist
    protected void onCreate() {
        if (fechaTransaccion == null) {
            fechaTransaccion = LocalDateTime.now();
        }
    }
}