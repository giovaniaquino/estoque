package com.projeto.estoque.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "idProduto", fetch = FetchType.LAZY)
    private List<LotesEntity> lotesEntityList;

    public ProdutoEntity() {
    }

    public ProdutoEntity(String nome, String quantidade, String lote, LocalDate fabricacao, LocalDate validade) {
        this.nome = nome;
        this.quantidade = quantidade;
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

    public List<LotesEntity> getLotesEntityList() {
        return lotesEntityList;
    }

    public void setLotesEntityList(List<LotesEntity> lotesEntityList) {
        this.lotesEntityList = lotesEntityList;
    }
}
