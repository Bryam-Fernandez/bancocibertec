package com.cibertec.bancocibertec.controllers;

import com.cibertec.bancocibertec.DTO.AperturaCuentaForm;
import com.cibertec.bancocibertec.persistence.entities.Cliente;
import com.cibertec.bancocibertec.persistence.entities.Cuenta;
import com.cibertec.bancocibertec.services.CuentaService;
import com.cibertec.bancocibertec.persistence.repositories.ClienteRepository;
import com.cibertec.bancocibertec.persistence.repositories.CuentaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;
    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;

    public CuentaController(CuentaService cuentaService,
                            ClienteRepository clienteRepository,
                            CuentaRepository cuentaRepository) {
        this.cuentaService = cuentaService;
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @GetMapping("/apertura")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cuentaForm", new AperturaCuentaForm());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "cuentas/apertura";
    }

    @PostMapping("/apertura")
    public String procesarFormulario(@ModelAttribute("cuentaForm") @Valid AperturaCuentaForm form,
                                     Model model) {
        try {
            cuentaService.abrirCuenta(form);
            return "redirect:/cuentas/lista";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("clientes", clienteRepository.findAll());
            return "cuentas/apertura";
        }
    }

    @GetMapping("/lista")
    public String seleccionarClienteParaListado(@RequestParam(value = "clienteId", required = false) Long clienteId,
                                                Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());

        if (clienteId != null) {
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            List<Cuenta> cuentas = cuentaRepository.findByCliente(cliente);

            model.addAttribute("cliente", cliente);
            model.addAttribute("cuentas", cuentas);
        }

        return "cuentas/seleccionar-cliente";
    }
    @GetMapping("/operaciones")
    public String mostrarOperaciones() {
        return "cuentas/operaciones";
    }

    @PostMapping("/depositar")
    public String depositar(@RequestParam String numeroCuenta,
                            @RequestParam BigDecimal monto,
                            Model model) {
        try {
            cuentaService.depositar(numeroCuenta, monto);
            model.addAttribute("mensaje", "Depósito exitoso");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "cuentas/operaciones";
    }

    @PostMapping("/retirar")
    public String retirar(@RequestParam String numeroCuenta,
                          @RequestParam BigDecimal monto,
                          Model model) {
        try {
            cuentaService.retirar(numeroCuenta, monto);
            model.addAttribute("mensaje", "Retiro exitoso");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "cuentas/operaciones";
    }

}
