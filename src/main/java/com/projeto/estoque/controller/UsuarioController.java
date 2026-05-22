package com.projeto.estoque.controller;

import com.projeto.estoque.dto.request.UsuarioCreateRequest;
import com.projeto.estoque.dto.request.UsuarioUpdateRequest;
import com.projeto.estoque.dto.response.UsuarioResponse;
import com.projeto.estoque.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(@RequestParam String email){
        service.deletarUsuario(email);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@RequestParam String email,@RequestBody UsuarioUpdateRequest request){
        return ResponseEntity.ok().body(service.atualizarUsuario(email,request));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscarUsuariosPorNome(@RequestParam String nome){
        return ResponseEntity.ok().body(service.listarUsuariosPorNome(nome));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioResponse>> buscarTodosUsuarios(){
        return ResponseEntity.ok().body(service.listarUsuarios());
    }
}
