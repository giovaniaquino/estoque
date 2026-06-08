package com.projeto.estoque.exceptions;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException(String mensagem){
        super(mensagem);
    }
}
