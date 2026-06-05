package com.projeto.estoque.dto.response;

import java.time.LocalDate;

public record LotesResponse(
        String Produto,
        String lote,
        int quantidade,
        LocalDate fabricacao,
        LocalDate validade
) {
}
