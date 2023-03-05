package com.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MST_month {

    @NotBlank
    private Integer month_id;

    @NotBlank
    private Integer month_year;

    @NotBlank
    private Integer month_month;

    @NotBlank
    public Integer month_period;


}
