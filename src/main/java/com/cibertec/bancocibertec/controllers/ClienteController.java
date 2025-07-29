package com.cibertec.bancocibertec.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.bancocibertec.persistence.entities.Cliente;
import com.cibertec.bancocibertec.persistence.enums.Enums.EstadoCliente;
import com.cibertec.bancocibertec.persistence.repositories.ClienteRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
private final ClienteRepository clienteRepository;

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/registro";
    }

    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute("cliente") @Valid Cliente cliente) {
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setEstado(EstadoCliente.ACTIVO); // Enum
        clienteRepository.save(cliente);
        return "redirect:/clientes/registro"; // o redirige a una lista
    }
}

