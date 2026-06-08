package com.projeto.estoque.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LotesCreateRequest(
        @NotBlank String codigoProduto,
        @NotBlank @Size(max = 20) String lote,
        @Min(value = 1, message = "Quantidade deve ser maior que zero") int quantidade,
        @NotNull LocalDate fabricacao,
        @NotNull LocalDate validade
        ) {
}
