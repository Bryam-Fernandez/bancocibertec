Eejercicio de Banco Cibertec 1.0

Descripción General
Desarrollar un sistema bancario básico utilizando JPA (Java Persistence API) que permita gestionar clientes, sus cuentas bancarias y los movimientos financieros asociados.
Objetivos de Aprendizaje
•Implementar relaciones JPA (OneToMany, ManyToOne)
•Manejar transacciones bancarias
•Aplicar validaciones y constraints
•Utilizar consultas JPQL
•Implementar auditoría básica
Modelo de Dominio
MVC 
Spring
Thymeleaf

Entidades Principales
1. Cliente
•Atributos: 
•id (Long): Identificador único
•numeroDocumento (String): Cédula/DNI/Pasaporte (único)
•tipoDocumento (Enum): CEDULA, DNI, PASAPORTE
•nombres (String): Nombres completos
•apellidos (String): Apellidos completos
•email (String): Correo electrónico (único)
•telefono (String): Número telefónico
•fechaNacimiento (LocalDate): Fecha de nacimiento
•fechaRegistro (LocalDateTime): Fecha de registro en el sistema
•estado (Enum): ACTIVO, INACTIVO, BLOQUEADO
•cuentas (List<Cuenta>): Lista de cuentas asociadas
2. Cuenta
•Atributos: 
•id (Long): Identificador único
•numeroCuenta (String): Número de cuenta (único)
•tipoCuenta (Enum): AHORRO, CORRIENTE, PLAZO_FIJO
•saldoActual (BigDecimal): Saldo actual
•fechaApertura (LocalDateTime): Fecha de apertura
•fechaUltimoMovimiento (LocalDateTime): Fecha del último movimiento
•estado (Enum): ACTIVA, INACTIVA, CERRADA, BLOQUEADA
•cliente (Cliente): Cliente propietario
•movimientos (List<Movimiento>): Lista de movimientos
3. Movimiento
•Atributos: 
•id (Long): Identificador único
•numeroTransaccion (String): Número único de transacción
•tipoMovimiento (Enum): DEPOSITO, RETIRO, TRANSFERENCIA_ENVIADA, TRANSFERENCIA_RECIBIDA
•monto (BigDecimal): Monto de la transacción
•montoAnterior (BigDecimal): Saldo anterior al movimiento
•montoNuevo (BigDecimal): Saldo después del movimiento
•descripcion (String): Descripción del movimiento
•fechaTransaccion (LocalDateTime): Fecha y hora de la transacción
•canal (Enum): CAJERO, VENTANILLA, ONLINE, MOBILE
•cuenta (Cuenta): Cuenta asociada
•cuentaDestino (String): Número de cuenta destino (para transferencias)


Especificaciones Técnicas
Validaciones Requeridas
•Cliente: 
•Número de documento único y obligatorio
•Email válido y único
•Fecha de nacimiento: cliente debe ser mayor de 18 años
•Nombres y apellidos obligatorios
•Cuenta: 
•Número de cuenta único (formato: XXXX-XXXX-XXXX)
•Saldo inicial mínimo según tipo de cuenta: 
•AHORRO: S/10
•CORRIENTE: S/1000
•PLAZO_FIJO S/ 5000
•Un cliente puede tener máximo 3 cuentas activas
•Movimiento: 
•Monto debe ser mayor a cero
•No permitir retiros que dejen saldo negativo
•Transferencias solo entre cuentas activas
•Generar número de transacción único
Reglas de Negocio
1.Apertura de Cuenta: 
1.Cliente debe estar activo
2.Validar saldo inicial mínimo
3.Asignar número de cuenta automáticamente
2.Depósitos: 
1.Monto mínimo: 100 soles
2.Monto máximo por transacción: 100.000.000 soles
3.Actualizar saldo y fecha último movimiento
3.Retiros: 
1.Monto mínimo: 50 soles
2.Validar saldo suficiente
3.Cuentas de ahorro: máximo 3 retiros mensuales
4.Transferencias: 
1.Validar cuenta origen y destino existan y estén activas
2.Aplicar débito a cuenta origen y crédito a cuenta destino
3.Generar dos movimientos (uno por cada cuenta)



<img width="602" height="381" alt="image" src="https://github.com/user-attachments/assets/3534d168-303b-4bd1-b1fd-84703afcbd94" />
<img width="683" height="366" alt="image" src="https://github.com/user-attachments/assets/bf1f9dcb-1e88-44ae-9edd-eba73cc5427b" />
