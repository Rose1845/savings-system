package com.rose.savings.service;

import com.rose.savings.mappers.SavingsProductMapper;
import com.rose.savings.model.dto.SavingsProductDto;
import com.rose.savings.model.entity.SavingsProduct;
import com.rose.savings.repository.SavingsProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavingsProductService {
    private final SavingsProductRepository savingsProductRepository;
    public SavingsProductDto createSavingProduct(SavingsProductDto savingsProductDto){
        SavingsProduct savingsProduct = SavingsProductMapper.MAPPER.toEntity(savingsProductDto);
        savingsProduct.setDescription(savingsProductDto.getDescription());
        savingsProduct.setName(savingsProductDto.getName());
        SavingsProduct savedProduct = savingsProductRepository.save(savingsProduct);
        return SavingsProductMapper.MAPPER.toDto(savedProduct);
    }
    public List<SavingsProduct> getAllSavingsProduct(){
        return new ArrayList<>(savingsProductRepository.findAll());
    }
    public Optional<SavingsProduct> getSavingsProduct(Long id){
        return savingsProductRepository.findById(id);
    }
    public String deleteSavingsProduct(Long id){
        savingsProductRepository.deleteById(id);
        return "Savings Product deleted successfully";
    }

}
