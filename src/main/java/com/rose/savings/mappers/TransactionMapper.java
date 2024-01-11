package com.rose.savings.mappers;

import com.rose.savings.model.dto.CustomerDto;
import com.rose.savings.model.dto.TransactionDto;
import com.rose.savings.model.entity.Customer;
import com.rose.savings.model.entity.Transaction;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    @Mappings({
            @Mapping(target = "customerId", source = "customer.customerId"),
    })
    TransactionDto toDto(Transaction transaction);

    @Mappings({
            @Mapping(target = "customer.customerId", source = "customerId"),
    })
    Transaction toEntity(TransactionDto transactionDto);

}