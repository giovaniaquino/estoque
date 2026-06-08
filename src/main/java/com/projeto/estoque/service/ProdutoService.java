package com.projeto.estoque.service;

import com.projeto.estoque.dto.mapper.ProdutoMapper;
import com.projeto.estoque.dto.request.ProdutoCreateRequest;
import com.projeto.estoque.dto.request.ProdutoUpdateRequest;
import com.projeto.estoque.dto.response.ProdutoResponse;
import com.projeto.estoque.exceptions.EntidadeNaoEncontradaException;
import com.projeto.estoque.exceptions.RegraDeNegocioException;
import com.projeto.estoque.model.ProdutoEntity;
import com.projeto.estoque.repository.LotesRepository;
import com.projeto.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final LotesRepository  lotesRepository;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper, LotesRepository lotesRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.lotesRepository = lotesRepository;
    }

    public ProdutoResponse criarProduto(ProdutoCreateRequest request) {
        ProdutoEntity saved = repository.save(mapper.paraProdutoEntity(request));
        return toResponseComQuantidade(saved);
    }

    public ProdutoResponse atualizarProduto(String codigo, ProdutoUpdateRequest request) {
        ProdutoEntity entity = repository.findByCodigo(codigo)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto para ser alterado não encontrado"));

        // Verifica se alteração é no código
        if (request.codigo() != null) {
            // Verifica se código já está cadastrado
            boolean codigoExiste = repository.existsByCodigo(request.codigo());
            if (codigoExiste) {
                throw new RegraDeNegocioException("Esse código já está cadastrado em outro produto");
            }
        }

        // Mescla a request com a entity antiga
        mapper.atualizarProdutoEntity(request, entity);

        ProdutoEntity produtoAtualizado = repository.save(entity);

        return toResponseComQuantidade(produtoAtualizado);
    }

    public List<ProdutoResponse> listarProdutoPorNome(String nome) {
        return repository.findByNomeContaining(nome).stream()
                .map(this::toResponseComQuantidade)
                .toList();
    }

    public List<ProdutoResponse> listarTodosProdutos() {
        return repository.findAll().stream()
                .map(this::toResponseComQuantidade)
                .toList();
    }

    private ProdutoResponse toResponseComQuantidade(ProdutoEntity entity) {
        ProdutoResponse base = mapper.paraProdutoResponse(entity);
        int quantidade = lotesRepository.sumQuantidadeNaoVencidosByProdutoId(entity.getId(), LocalDate.now());
        return new ProdutoResponse(base.codigo(), base.nome(), quantidade);
    }
}
