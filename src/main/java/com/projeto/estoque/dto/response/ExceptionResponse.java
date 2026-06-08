package com.projeto.estoque.dto.response;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String message
) {
    public static ExceptionResponse of(Integer status, String erro, String message){
        return new ExceptionResponse(LocalDateTime.now(), status, erro, message);
    }
}
