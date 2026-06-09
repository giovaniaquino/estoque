package com.projeto.estoque.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class LotesRepositoryTest {

    @Test
    void sumQuantidadeNaoVencidosByProdutoId() {
    }
}