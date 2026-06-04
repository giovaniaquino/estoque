package com.projeto.estoque.dto.request;

import jakarta.validation.constraints.Size;

public record ProdutoUpdateRequest(@Size(max = 20) String codigo, String nome) {
}
