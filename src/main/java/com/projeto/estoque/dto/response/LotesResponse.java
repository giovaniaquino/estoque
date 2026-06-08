package com.projeto.estoque.dto.response;

import java.time.LocalDate;

public record LotesResponse(
        String nomeProduto,
        String lote,
        int quantidade,
        LocalDate fabricacao,
        LocalDate validade
) {
}
