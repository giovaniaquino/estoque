package com.projeto.estoque.repository;

import com.projeto.estoque.model.LotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LotesRepository extends JpaRepository<LotesEntity, Long> {

    List<LotesEntity> findByProdutoCodigo(String codigo);

    @Query("SELECT COALESCE(SUM(l.quantidade), 0) FROM LotesEntity l WHERE l.produto.id = :produtoId AND l.validade >= :hoje")
    int sumQuantidadeNaoVencidosByProdutoId(@Param("produtoId") Long produtoId, @Param("hoje") LocalDate hoje);
}
