package com.projeto.estoque.repository;

import com.projeto.estoque.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    List<ProdutoEntity> findByNomeContaining(String nome);
}
