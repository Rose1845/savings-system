package com.rose.savings.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotEmpty
    @NotBlank
    @NotNull
    private String name;
    @NotEmpty
    @NotBlank
    @NotNull
    @Min(8)
    @Column(unique = true)
    private String idNumber;
    @NotEmpty
    @NotBlank
    @NotNull
    @Min(10)
    @Column(unique = true)
    private String phoneNumber;
    @NotEmpty
    @NotBlank
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @NotEmpty
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String memberNumber;
}
