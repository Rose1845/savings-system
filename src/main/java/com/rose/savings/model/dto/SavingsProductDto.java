package com.rose.savings.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingsProductDto {
    @NotEmpty
    @NotBlank
    @NotNull
    private String name;
    @NotEmpty
    @NotBlank
    @NotNull
    private String description;
}
