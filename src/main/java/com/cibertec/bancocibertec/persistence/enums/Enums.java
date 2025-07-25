package com.cibertec.bancocibertec.persistence.enums;


public class Enums
{
    public enum TipoDocumento {
        CEDULA("Cédula de Ciudadanía"),
        DNI("Documento Nacional de Identidad"),
        PASAPORTE("Pasaporte");

        private final String descripcion;

        TipoDocumento(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    /**
     * Estados posibles de un cliente
     */
    public enum EstadoCliente {
        ACTIVO("Cliente Activo"),
        INACTIVO("Cliente Inactivo"),
        BLOQUEADO("Cliente Bloqueado");

        private final String descripcion;

        EstadoCliente(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    /**
     * Tipos de cuenta bancaria
     */
    public enum TipoCuenta {
        AHORRO("Cuenta de Ahorros"),
        CORRIENTE("Cuenta Corriente"),
        PLAZO_FIJO("Cuenta a Plazo Fijo");

        private final String descripcion;

        TipoCuenta(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    /**
     * Estados posibles de una cuenta
     */
    public enum EstadoCuenta {
        ACTIVA("Cuenta Activa"),
        INACTIVA("Cuenta Inactiva"),
        CERRADA("Cuenta Cerrada"),
        BLOQUEADA("Cuenta Bloqueada");

        private final String descripcion;

        EstadoCuenta(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    /**
     * Tipos de movimientos bancarios
     */
    public enum TipoMovimiento {
        DEPOSITO("Depósito"),
        RETIRO("Retiro"),
        TRANSFERENCIA_ENVIADA("Transferencia Enviada"),
        TRANSFERENCIA_RECIBIDA("Transferencia Recibida");

        private final String descripcion;

        TipoMovimiento(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    /**
     * Canales de transacción
     */
    public enum Canal {
        CAJERO("Cajero Automático"),
        VENTANILLA("Ventanilla Presencial"),
        ONLINE("Banca Online"),
        MOBILE("Aplicación Móvil");

        private final String descripcion;

        Canal(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
