package com.projeto.estoque.controller;

import com.projeto.estoque.dto.request.LoginRequest;
import com.projeto.estoque.dto.request.UsuarioCreateRequest;
import com.projeto.estoque.dto.response.LoginResponse;
import com.projeto.estoque.dto.response.UsuarioResponse;
import com.projeto.estoque.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService  service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok().body(service.login(request));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarUsuario(request));
    }
}
