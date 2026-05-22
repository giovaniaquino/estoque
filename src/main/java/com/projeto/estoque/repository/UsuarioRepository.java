package com.projeto.estoque.repository;

import com.projeto.estoque.model.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UserDetails> findByEmail(String username);

    @Transactional
    void deleteByEmail(String email);

    // Mostra todos usuario que contem nome
    List<UsuarioEntity> findByNomeContaining(String nome);

    boolean existsByEmail(String email);
}
