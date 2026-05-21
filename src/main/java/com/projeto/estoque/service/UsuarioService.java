package com.projeto.estoque.service;

import com.projeto.estoque.dto.mapper.UsuarioMapper;
import com.projeto.estoque.dto.request.UsuarioCreateRequest;
import com.projeto.estoque.dto.request.UsuarioUpdateRequest;
import com.projeto.estoque.dto.response.UsuarioResponse;
import com.projeto.estoque.model.UsuarioEntity;
import com.projeto.estoque.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UsuarioResponse criarUsuario(UsuarioCreateRequest request) {
        return mapper.paraUsuarioResponse(
                repository.save(
                        mapper.paraUsuarioEntity(request)));
    }

    public UsuarioResponse atualizarUsuario(String email, UsuarioUpdateRequest request) {
        UsuarioEntity entity = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Mescla a request com a entity antiga
        mapper.atualizarUsuarioEntity(request, entity);

        UsuarioEntity usuarioAtualizado = repository.save(entity);

        return mapper.paraUsuarioResponse(usuarioAtualizado);
    }

    public void deletarUsuario(String email) {
        repository.deleteByEmail(email);
    }

    public List<UsuarioResponse> listarUsuariosPorNome(String nome) {
        return mapper.paraListaUsuarioResponse(repository.findByNomeContaining(nome));
    }

    public List<UsuarioResponse> listarUsuarios(){
        return mapper.paraListaUsuarioResponse(repository.findAll());
    }
}
