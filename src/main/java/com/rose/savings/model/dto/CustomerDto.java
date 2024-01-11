package com.rose.savings.model.dto;

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
    private String idNumber;
    @NotEmpty
    @NotBlank
    @NotNull
    @Min(10)
    private String phoneNumber;
    @NotEmpty
    @NotBlank
    @NotNull
    @Email
    private String email;
    @NotEmpty
    @NotBlank
    @NotNull
    private String memberNumber;
}
