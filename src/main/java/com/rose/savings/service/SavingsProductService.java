package com.rose.savings.service;

import com.rose.savings.advice.SavingsException;
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
    /**
     * logic to create a savings product
     * **/
    public SavingsProductDto createSavingProduct(SavingsProductDto savingsProductDto) throws SavingsException {
        if (savingsProductRepository.existsByName(savingsProductDto.getName())) {
            throw  SavingsException.builder().message("saving product with that name already exists number already in use").metadata("saving product with that name exists").build();
        }
        SavingsProduct savingsProduct = SavingsProductMapper.MAPPER.toEntity(savingsProductDto);
        savingsProduct.setDescription(savingsProductDto.getDescription());
        savingsProduct.setName(savingsProductDto.getName());
        SavingsProduct savedProduct = savingsProductRepository.save(savingsProduct);
        return SavingsProductMapper.MAPPER.toDto(savedProduct);
    }
    /**
     * logic to retrieve all savings product
     * **/
    public List<SavingsProduct> getAllSavingsProduct(){
        return new ArrayList<>(savingsProductRepository.findAll());
    }
    /**
     * logic to retrieve a saving-product a specific savings-products
     * **/
    public Optional<SavingsProduct> getSavingsProduct(Long id) throws SavingsException {
        Optional<SavingsProduct> savingsProduct = savingsProductRepository.findById(id);
        if(savingsProduct.isEmpty()){
            throw SavingsException.builder()
                    .message("savings with id do not  exist")
                    .metadata("get savings product")
                    .build();
        }

        return savingsProductRepository.findById(id);
    }
    /**
     * logic to delete specific savings-product
     **/
    public void deleteSavingsProduct(Long id) throws SavingsException {
        Optional<SavingsProduct> savingsProduct = savingsProductRepository.findById(id);
        if(savingsProduct.isEmpty()){
            throw SavingsException.builder()
                    .message("savings product with id do not  exists")
                    .metadata("delete transaction")
                    .build();
        }
        savingsProductRepository.deleteById(id);
    }
    public SavingsProduct updateSavingsProduct(Long id, SavingsProductDto savingsProductDto) throws SavingsException {
        Optional<SavingsProduct> savingsProduct = savingsProductRepository.findById(id);
        if(savingsProduct.isEmpty()){
            throw SavingsException.builder()
                    .message("savings with id do not  exist")
                    .metadata("get savings product")
                    .build();
        }
        SavingsProduct savingsProduct1 = new SavingsProduct();
        savingsProduct1.setDescription(savingsProductDto.getDescription());
        savingsProduct1.setName(savingsProductDto.getName());
        return savingsProductRepository.save(savingsProduct1);

    }


    public SavingsProduct updateSavingsProducts(SavingsProduct savingsProduct) {
        return savingsProductRepository.save(savingsProduct);
    }
}
