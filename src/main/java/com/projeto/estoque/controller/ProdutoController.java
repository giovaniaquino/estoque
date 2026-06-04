package com.projeto.estoque.controller;

import com.projeto.estoque.dto.request.ProdutoCreateRequest;
import com.projeto.estoque.dto.request.ProdutoUpdateRequest;
import com.projeto.estoque.dto.response.ProdutoResponse;
import com.projeto.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ProdutoResponse criarProduto(@Valid @RequestBody ProdutoCreateRequest request){
        return service.criarProduto(request);
    }

    @PatchMapping
    public ProdutoResponse atualizarProduto(@RequestParam String codigo, @Valid @RequestBody ProdutoUpdateRequest request){
        return service.atualizarProduto(codigo, request);
    }

    @GetMapping
    public List<ProdutoResponse> listarProdutoPorNome(@RequestParam String nome) {
        return service.listarProdutoPorNome(nome);
    }

    @GetMapping("/all")
    public List<ProdutoResponse> listarTodosProdutos(){
        return service.listarTodosProdutos();
    }
}
