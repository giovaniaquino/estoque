package com.projeto.estoque.controller;

import com.projeto.estoque.dto.request.UsuarioCreateRequest;
import com.projeto.estoque.dto.request.UsuarioUpdateRequest;
import com.projeto.estoque.dto.response.UsuarioResponse;
import com.projeto.estoque.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody UsuarioCreateRequest request){
        return ResponseEntity.ok().body(service.criarUsuario(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(@RequestParam String email){
        service.deletarUsuario(email);
        return ResponseEntity.ok().build();
    }
}
