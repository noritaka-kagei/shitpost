package com.noritakakagei.sample.form;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalcForm {
    @NotNull(message = "input the number to the left side")
    @Range(min = 1, max = 10, message = "input the number in {min}~{max} to the left side")
    private Integer leftNum;
    @NotNull(message = "input the number to the right side")
    @Range(min = 1, max = 10, message = "input the number in {min}~{max} to the right side")
    private Integer rightNum;
}