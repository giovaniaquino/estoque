package com.projeto.estoque.repository;

import com.projeto.estoque.model.LotesEntity;
import com.projeto.estoque.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotesRepository extends JpaRepository<LotesEntity, Long> {

    List<LotesEntity> findByCodigo(String codigo);
}
