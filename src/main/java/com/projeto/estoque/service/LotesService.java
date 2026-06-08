package com.projeto.estoque.service;

import com.projeto.estoque.dto.mapper.LotesMapper;
import com.projeto.estoque.dto.request.LotesCreateRequest;
import com.projeto.estoque.dto.response.LotesResponse;
import com.projeto.estoque.exceptions.EntidadeNaoEncontradaException;
import com.projeto.estoque.exceptions.RegraDeNegocioException;
import com.projeto.estoque.model.LotesEntity;
import com.projeto.estoque.model.ProdutoEntity;
import com.projeto.estoque.repository.LotesRepository;
import com.projeto.estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotesService {

    private final LotesRepository repository;
    private final ProdutoRepository produtoRepository;
    private final LotesMapper mapper;

    public LotesService(LotesRepository repository, LotesMapper mapper,  ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public LotesResponse criarLote(LotesCreateRequest request){
        ProdutoEntity produto = produtoRepository.findByCodigo(request.codigoProduto())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Código de produto não existe"));

        // Verifica se validade está depois de fabricação
        if (!request.fabricacao().isBefore(request.validade())) throw new RegraDeNegocioException("Validade deve ser depois da fabricação");

        LotesEntity lote = mapper.paraLotesEntity(request);
        lote.setProduto(produto);
        return mapper.paraLotesResponse(repository.save(lote));
    }

    public List<LotesResponse> listarLotesDoProduto(String codigoProduto){
        // Verificar se produto existe
        if (!verificarProduto(codigoProduto)) throw new EntidadeNaoEncontradaException("Código de produto não existe");

        return mapper.paraListaLotesResponse(repository.findByProdutoCodigo(codigoProduto));
    }

    private boolean verificarProduto(String codigoProduto){
        return produtoRepository.existsByCodigo(codigoProduto);
    }
}
