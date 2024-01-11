package com.rose.savings.mappers;

import com.rose.savings.model.dto.CustomerDto;
import com.rose.savings.model.entity.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerDto customerDto);
    CustomerDto toDto(Customer customer);
}
