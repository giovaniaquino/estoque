package com.projeto.estoque.dto.request;

import jakarta.validation.constraints.Size;

public record UsuarioUpdateRequest(
        @Size(max = 80) String nome,
        String email,
        String senha
) {
}
