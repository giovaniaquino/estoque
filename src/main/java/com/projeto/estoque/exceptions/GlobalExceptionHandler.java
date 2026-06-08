package com.projeto.estoque.exceptions;

import com.projeto.estoque.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Entidade não encontrada
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ExceptionResponse> handleNaoEncontrado(EntidadeNaoEncontradaException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.of(404,"Não encontrado", ex.getMessage()));
    }

    // Erro na regra de negócio (email duplicado)
    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ExceptionResponse> handleRegraDeNegocio(RegraDeNegocioException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.of(409, "Conflito",  ex.getMessage()));
    }

    // Erro na validação @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidacao(MethodArgumentNotValidException ex){
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.of(400, "Dados inválidos", mensagem));
    }

    // Erro no login
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleCredenciaisInvalidas(BadCredentialsException ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.of(401, "Não autorizado", "Email ou senha inválido"));
    }

    // Retorno genérico 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleErroGenerico(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.of(500, "Erro interno", "Ocorreu um erro inesperado"));
    }
}
