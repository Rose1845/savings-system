package com.rose.savings.controller;

import com.rose.savings.model.dto.SavingsProductDto;
import com.rose.savings.model.entity.SavingsProduct;
import com.rose.savings.service.SavingsProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/savings-products")
@RequiredArgsConstructor
public class SavingsProductController {
    private final SavingsProductService savingsProductService;
    @PostMapping
    public SavingsProductDto createSavingsProduct(@RequestBody @Valid SavingsProductDto savingsProductDto){
        return savingsProductService.createSavingProduct(savingsProductDto);
    }
    @GetMapping
    public List<SavingsProduct> getAllSavingsProduct(){
        return savingsProductService.getAllSavingsProduct();
    }
    @GetMapping("{id}/savings-product")
    public Optional<SavingsProduct> getSavingsProducts(@PathVariable("id") @Valid Long id){
        return savingsProductService.getSavingsProduct(id);
    }


}
