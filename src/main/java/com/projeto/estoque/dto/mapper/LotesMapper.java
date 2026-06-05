package com.projeto.estoque.dto.mapper;

import com.projeto.estoque.dto.request.LotesCreateRequest;
import com.projeto.estoque.dto.response.LotesResponse;
import com.projeto.estoque.model.LotesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LotesMapper {

    @Mapping(target = "id", ignore = true)
    LotesEntity paraLotesEntity(LotesCreateRequest request);

    LotesResponse paraLotesResponse(LotesEntity entity);

    List<LotesResponse> paraListaLotesResponse(List<LotesEntity> entityList);
}
