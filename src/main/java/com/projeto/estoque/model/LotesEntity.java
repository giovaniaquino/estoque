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
    @Column(nullable = false)
    private LocalDate fabricacao;
    @Column(nullable = false)
    private LocalDate validade;
    @Column(nullable = false, name = "id_produto")
    private int idProduto;

    public LotesEntity() {
    }

    public LotesEntity(Long id, String lote, LocalDate fabricacao, LocalDate validade, int idProduto) {
        this.id = id;
        this.lote = lote;
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

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
}
