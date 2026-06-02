package com.projeto.estoque.dto.response;

import java.time.LocalDateTime;

public record ExeptionResponse(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String message
) {
    public static ExeptionResponse of(Integer status, String erro, String message){
        return new ExeptionResponse(LocalDateTime.now(), status, erro, message);
    }
}
