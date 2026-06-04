package com.projeto.estoque.service;

import com.projeto.estoque.dto.mapper.ProdutoMapper;
import com.projeto.estoque.dto.request.ProdutoCreateRequest;
import com.projeto.estoque.dto.request.ProdutoUpdateRequest;
import com.projeto.estoque.dto.response.ProdutoResponse;
import com.projeto.estoque.exceptions.EntidadeNaoEncontradaException;
import com.projeto.estoque.exceptions.RegraDeNegocioExeption;
import com.projeto.estoque.model.ProdutoEntity;
import com.projeto.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public ProdutoResponse atualizarProduto(String codigo, ProdutoUpdateRequest request) {
        ProdutoEntity entity = repository.findByCodigo(codigo)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto para ser alterado não encontrado"));

        // Verifica se alteração é no código
        if (request.codigo() != null) {
            // Verifica se código já está cadastrado
            boolean codigoExiste = repository.existsByCodigo(request.codigo());
            if (codigoExiste) {
                throw new RegraDeNegocioExeption("Esse código já está cadastrado em outro produto");
            }
        }

        // Mescla a request com a entity antiga
        mapper.atualizarProdutoEntity(request, entity);

        ProdutoEntity produtoAtualizado = repository.save(entity);

        return mapper.paraProdutoResponse(produtoAtualizado);
    }

    public List<ProdutoResponse> listarProdutoPorNome(String nome) {
        return mapper.paraListaProdutoResponse(repository.findByNomeContaining(nome));
    }

    public List<ProdutoResponse> listarTodosProdutos() {
        return mapper.paraListaProdutoResponse(repository.findAll());
    }
}
