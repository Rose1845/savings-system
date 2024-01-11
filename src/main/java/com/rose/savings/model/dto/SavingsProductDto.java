package com.rose.savings.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingsProductDto {
    private String name;
    private String description;
}
