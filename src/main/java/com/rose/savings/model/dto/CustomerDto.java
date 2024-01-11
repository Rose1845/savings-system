package com.rose.savings.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private String memberNumber;
}
