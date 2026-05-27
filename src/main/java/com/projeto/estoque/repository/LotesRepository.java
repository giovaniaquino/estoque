package com.projeto.estoque.repository;

import com.projeto.estoque.model.LotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotesRepository extends JpaRepository<LotesEntity, Long> {
}
