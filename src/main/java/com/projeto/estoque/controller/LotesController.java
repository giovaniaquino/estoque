package com.projeto.estoque.controller;

import com.projeto.estoque.dto.request.LotesCreateRequest;
import com.projeto.estoque.dto.response.LotesResponse;
import com.projeto.estoque.service.LotesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotes")
public class LotesController {

    private final LotesService service;

    public LotesController(LotesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LotesResponse> criarLote(@Valid @RequestBody LotesCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarLote(request));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<LotesResponse>> listarLotesPorProduto(@RequestParam String codigoProduto){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarLotesDoProduto(codigoProduto));
    }



}
