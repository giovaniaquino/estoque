package com.projeto.estoque.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreateRequest(
        @NotBlank @Size(max = 80) String nome,
        @NotBlank String email,
        @NotBlank String senha
) {
}
