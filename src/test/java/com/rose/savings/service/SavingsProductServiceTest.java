package com.rose.savings.service;

import com.rose.savings.advice.SavingsException;
import com.rose.savings.model.entity.SavingsProduct;
import com.rose.savings.repository.SavingsProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SavingsProductServiceTest {
    @Mock
    private SavingsProductRepository savingsProductRepository;

    @InjectMocks
    private SavingsProductService savingsProductService;
    private SavingsProduct savingsProduct;

    @BeforeEach
    public void setup(){

        savingsProduct = SavingsProduct.builder()
                .id(1L)
                .name("rose")
                .description("test descv")
                .build();
    }
    @Test
    void updateSavingsProduct() {
        given(savingsProductRepository.save(savingsProduct)).willReturn(savingsProduct);
        savingsProduct.setName("rose");
        savingsProduct.setDescription("test");
        // when -  action or the behaviour that we are going test
        SavingsProduct updatedProduct = savingsProductService.updateSavingsProducts(savingsProduct);

        // then - verify the output
        assertThat(updatedProduct.getName()).isEqualTo("rose");
        assertThat(updatedProduct.getDescription()).isEqualTo("test");
    }

}