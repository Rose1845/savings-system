package com.rose.savings.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SavingsException extends Throwable {
    /**
     * @param message
     */
    public SavingsException(String message) {
        super(message);
    }
    private Integer statusCode=500;
    private String metadata="No metadata";
    private String message="Internal server error";
}

