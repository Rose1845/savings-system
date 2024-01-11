package com.rose.savings.mappers;

import com.rose.savings.model.dto.SavingsProductDto;
import com.rose.savings.model.entity.SavingsProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SavingsProductMapper {
    SavingsProductMapper MAPPER = Mappers.getMapper(SavingsProductMapper.class);

    SavingsProduct toEntity(SavingsProductDto savingsProductDto);
    SavingsProductDto toDto(SavingsProduct savingsProduct);
}
