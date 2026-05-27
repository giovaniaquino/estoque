package com.projeto.estoque.repository;

import com.projeto.estoque.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByNomeContaining(String nome);
}
