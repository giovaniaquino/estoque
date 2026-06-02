package com.projeto.estoque.exceptions;

public class RegraDeNegocioExeption extends RuntimeException{
    public RegraDeNegocioExeption(String mensagem){
        super(mensagem);
    }
}
