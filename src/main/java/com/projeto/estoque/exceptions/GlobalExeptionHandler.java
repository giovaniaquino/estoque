package com.projeto.estoque.exceptions;

import com.projeto.estoque.dto.response.ExeptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExeptionHandler {

    // Entidade não encontrada
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ExeptionResponse> handleNaoEncontrado(EntidadeNaoEncontradaException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExeptionResponse.of(404,"Não encontrado", ex.getMessage()));
    }

    // Erro na regra de negócio (email duplicado)
    @ExceptionHandler(RegraDeNegocioExeption.class)
    public ResponseEntity<ExeptionResponse> handleRegraDeNegocio(RegraDeNegocioExeption ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ExeptionResponse.of(409, "Conflito",  ex.getMessage()));
    }

    // Erro na validação @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExeptionResponse> handleValidacao(MethodArgumentNotValidException ex){
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExeptionResponse.of(400, "Dados inválidos", mensagem));
    }

    // Erro no login
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExeptionResponse> handleCredenciaisInvalidas(BadCredentialsException ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExeptionResponse.of(401, "Não autorizado", "Email ou senha inválido"));
    }

    // Retorno genérico 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExeptionResponse> handleErroGenerico(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExeptionResponse.of(500, "Erro interno", "Ocorreu um erro inesperado"));
    }
}
