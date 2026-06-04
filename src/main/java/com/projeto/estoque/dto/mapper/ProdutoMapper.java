package com.projeto.estoque.dto.mapper;

import com.projeto.estoque.dto.request.ProdutoCreateRequest;
import com.projeto.estoque.dto.response.ProdutoResponse;
import com.projeto.estoque.model.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    ProdutoEntity paraProdutoEntity(ProdutoCreateRequest request);

    ProdutoResponse paraProdutoResponse(ProdutoEntity produtoEntity);
}
