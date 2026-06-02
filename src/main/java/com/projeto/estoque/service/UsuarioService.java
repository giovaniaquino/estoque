package com.projeto.estoque.service;

import com.projeto.estoque.config.TokenConfig;
import com.projeto.estoque.dto.mapper.UsuarioMapper;
import com.projeto.estoque.dto.request.LoginRequest;
import com.projeto.estoque.dto.request.UsuarioCreateRequest;
import com.projeto.estoque.dto.request.UsuarioUpdateRequest;
import com.projeto.estoque.dto.response.LoginResponse;
import com.projeto.estoque.dto.response.UsuarioResponse;
import com.projeto.estoque.exceptions.EntidadeNaoEncontradaException;
import com.projeto.estoque.exceptions.RegraDeNegocioExeption;
import com.projeto.estoque.model.UsuarioEntity;
import com.projeto.estoque.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper,  AuthenticationManager authenticationManager, TokenConfig tokenConfig,  PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(@Valid LoginRequest request){
        UsernamePasswordAuthenticationToken emailSenha = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(emailSenha);

        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();
        String token = tokenConfig.gerarToken(usuario);
        return new LoginResponse(token);
    }

    public UsuarioResponse registrarUsuario(@Valid UsuarioCreateRequest request){
        if (repository.existsByEmail(request.email())) throw new RegraDeNegocioExeption("Email já está em uso");

        UsuarioEntity novoUsuario = new  UsuarioEntity();
        novoUsuario.setNome(request.nome());
        novoUsuario.setEmail(request.email());
        novoUsuario.setSenha(passwordEncoder.encode(request.senha()));
        repository.save(novoUsuario);

        return new UsuarioResponse(novoUsuario.getNome(), novoUsuario.getEmail());
    }

    public UsuarioResponse atualizarUsuario(String email, UsuarioUpdateRequest request) {
        UsuarioEntity entity = repository.findByEmail(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        // Verifica e alteração é no email
        if (request.email() != null) {
            // Verifica se email já está cadastrado
            boolean emailExitente = repository.existsByEmail(request.email());
            if (emailExitente) {
                throw new RegraDeNegocioExeption("Esse email já está cadastrado");
            }
        }

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
