package com.app.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TRN_shop {

    @NotBlank
    private Integer shop_id;
    
    @NotBlank
    private String shop_name;
    
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private LocalDate open_date;
    
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate close_date;
    
    private String promotion_key;
    
    private String shop_status;
    
    //合体して取ってくる
    private Integer rep_id;
    
    private String rep_name;

}
