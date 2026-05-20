package com.projeto.estoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String quantidade;
    @Column(nullable = false, length = 20)
    private String lote;
    @Column(nullable = false)
    private LocalDate fabricacao;
    @Column(nullable = false)
    private LocalDate validade;

    public ProdutoEntity() {
    }

    public ProdutoEntity(String nome, String quantidade, String lote, LocalDate fabricacao, LocalDate validade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.lote = lote;
        this.fabricacao = fabricacao;
        this.validade = validade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(LocalDate fabricacao) {
        this.fabricacao = fabricacao;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }
}
