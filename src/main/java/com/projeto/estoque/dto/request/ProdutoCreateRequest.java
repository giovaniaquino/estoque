package com.projeto.estoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProdutoCreateRequest(
        @NotBlank @Size(max = 20) String codigo,
        @NotBlank String nome) {
}
