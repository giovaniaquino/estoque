package com.projeto.estoque.service;

import com.projeto.estoque.dto.mapper.ProdutoMapper;
import com.projeto.estoque.dto.request.ProdutoCreateRequest;
import com.projeto.estoque.dto.response.ProdutoResponse;
import com.projeto.estoque.repository.ProdutoRepository;

public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProdutoResponse criarProduto(ProdutoCreateRequest request) {
        return mapper.paraProdutoResponse(repository.save(mapper.paraProdutoEntity(request)));
    }
}
