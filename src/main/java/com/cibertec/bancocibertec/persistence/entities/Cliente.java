package com.cibertec.bancocibertec.persistence.entities;

import jakarta.persistence.*;
import com.cibertec.bancocibertec.persistence.enums.Enums.*;
import com.cibertec.bancocibertec.persistence.entities.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import java.time.LocalDate;


@Setter
@Getter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoDocumento tipoDocumento;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String telefono;

    @Past
    @NotNull
    private LocalDate fechaNacimiento;

    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentas;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoCliente.ACTIVO;
        }
    }

    // Getters and setters
}
