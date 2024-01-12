package com.rose.savings.controller;

import com.rose.savings.advice.SavingsException;
import com.rose.savings.model.dto.SavingsProductDto;
import com.rose.savings.model.entity.SavingsProduct;
import com.rose.savings.service.SavingsProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/savings-products")
@RequiredArgsConstructor
public class SavingsProductController {
    private final SavingsProductService savingsProductService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public SavingsProductDto createSavingsProduct(@RequestBody @Valid SavingsProductDto savingsProductDto) throws SavingsException {
        return savingsProductService.createSavingProduct(savingsProductDto);
    }
    @GetMapping
    public List<SavingsProduct> getAllSavingsProduct(){
        return savingsProductService.getAllSavingsProduct();
    }
    @GetMapping("{id}/savings-product")
    public Optional<SavingsProduct> getSavingsProducts(@PathVariable("id") @Valid Long id) throws SavingsException {
        return savingsProductService.getSavingsProduct(id);
    }
    @DeleteMapping("{id}")
    public String deleteSavingsProduct(@PathVariable("id") @Valid Long id) throws SavingsException {
        savingsProductService.deleteSavingsProduct(id);
        return "Deleted successfully";
    }
    @PutMapping("{id}/update-savings-product")
    public  SavingsProduct updateSavingsProduct(@PathVariable("id") Long id, @RequestBody @Valid SavingsProductDto savingsProductDto) throws SavingsException {
        return savingsProductService.updateSavingsProduct(id,savingsProductDto);
    }

}
