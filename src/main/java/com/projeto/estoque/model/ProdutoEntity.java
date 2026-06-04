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
    @Column(nullable = false, length = 20)
    private String codigo;
    @Column(nullable = false)
    private String nome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "idProduto", fetch = FetchType.LAZY)
    private List<LotesEntity> lotesEntityList;

    public ProdutoEntity() {
    }

    public ProdutoEntity(String codigo, String nome, Integer quantidade) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LotesEntity> getLotesEntityList() {
        return lotesEntityList;
    }

    public void setLotesEntityList(List<LotesEntity> lotesEntityList) {
        this.lotesEntityList = lotesEntityList;
    }
}
