package com.projeto.estoque.controller;

import com.projeto.estoque.dto.request.LoginRequest;
import com.projeto.estoque.dto.request.UsuarioCreateRequest;
import com.projeto.estoque.dto.response.LoginResponse;
import com.projeto.estoque.dto.response.UsuarioResponse;
import com.projeto.estoque.model.UsuarioEntity;
import com.projeto.estoque.repository.UsuarioRepository;
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

    private final UsuarioRepository repository;

    public AuthController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioCreateRequest request){
        UsuarioEntity novoUsuario = new  UsuarioEntity();
        novoUsuario.setNome(request.nome());
        novoUsuario.setSenha(request.senha());
        novoUsuario.setEmail(request.email());
        repository.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new UsuarioResponse(novoUsuario.getNome(), novoUsuario.getEmail()));
    }
}
