package com.projeto.estoque.repository;

import com.projeto.estoque.model.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    // Mostra todos usuario que contem nome
    List<UsuarioEntity> findByNomeContaining(String nome);
}
