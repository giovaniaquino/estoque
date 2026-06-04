package com.projeto.estoque.dto.mapper;

import com.projeto.estoque.dto.request.ProdutoCreateRequest;
import com.projeto.estoque.dto.request.ProdutoUpdateRequest;
import com.projeto.estoque.dto.response.ProdutoResponse;
import com.projeto.estoque.model.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    ProdutoEntity paraProdutoEntity(ProdutoCreateRequest request);

    ProdutoResponse paraProdutoResponse(ProdutoEntity entity);

    @Mapping(target = "id", ignore = true)
    void atualizarProdutoEntity(ProdutoUpdateRequest request, @MappingTarget ProdutoEntity entity);

    List<ProdutoResponse> paraListaProdutoResponse(List<ProdutoEntity> entityList);
}
