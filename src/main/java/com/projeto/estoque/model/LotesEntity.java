package com.projeto.estoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotes")
public class LotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Long id;
    @Column(unique = true, nullable = false, length = 20)
    private String lote;
    @Column
    private int quantidade;
    @Column(nullable = false)
    private LocalDate fabricacao;
    @Column(nullable = false)
    private LocalDate validade;
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_produto")
    private ProdutoEntity idProduto;

    public LotesEntity() {
    }

    public LotesEntity(Long id, String lote, int quantidade, LocalDate fabricacao, LocalDate validade, ProdutoEntity idProduto) {
        this.id = id;
        this.lote = lote;
        this.quantidade = quantidade;
        this.fabricacao = fabricacao;
        this.validade = validade;
        this.idProduto = idProduto;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoEntity getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(ProdutoEntity idProduto) {
        this.idProduto = idProduto;
    }
}
