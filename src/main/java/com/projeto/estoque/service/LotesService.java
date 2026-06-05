package com.projeto.estoque.service;

import com.projeto.estoque.dto.mapper.LotesMapper;
import com.projeto.estoque.dto.request.LotesCreateRequest;
import com.projeto.estoque.dto.response.LotesResponse;
import com.projeto.estoque.exceptions.EntidadeNaoEncontradaException;
import com.projeto.estoque.exceptions.RegraDeNegocioExeption;
import com.projeto.estoque.repository.LotesRepository;
import com.projeto.estoque.repository.ProdutoRepository;
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

    public LotesResponse criarLote(LotesCreateRequest request){
        // Verificar se produto existe
        if (!verificarProduto(request.codigoProduto())) throw new EntidadeNaoEncontradaException("Código de produto não existe");
        // Verifica se datas de fabricação e validade estão corretas
        if (request.fabricacao().isBefore(request.validade())) throw new RegraDeNegocioExeption("Validade mais recente que fabricação");

        return mapper.paraLotesResponse(repository.save(mapper.paraLotesEntity(request)));
    }

    public List<LotesResponse> listarLotesDoProduto(String codigoProduto){
        // Verificar se produto existe
        if (!verificarProduto(codigoProduto)) throw new EntidadeNaoEncontradaException("Código de produto não existe");

        return mapper.paraListaLotesResponse(repository.findByCodigo(codigoProduto));
    }

    private boolean verificarProduto(String codigoProduto){
        return produtoRepository.existsByCodigo(codigoProduto);
    }
}
