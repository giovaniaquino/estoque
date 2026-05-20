package com.projeto.estoque.dto.mapper;

import com.projeto.estoque.dto.request.UsuarioCreateRequest;
import com.projeto.estoque.dto.request.UsuarioUpdateRequest;
import com.projeto.estoque.dto.response.UsuarioResponse;
import com.projeto.estoque.model.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    UsuarioEntity paraUsuarioEntity(UsuarioCreateRequest request);

    UsuarioResponse paraUsuarioResponse(UsuarioEntity entity);

    @Mapping(target = "id", ignore = true)
    void atualizarUsuarioEntity(UsuarioUpdateRequest request, @MappingTarget UsuarioEntity entity);
}
