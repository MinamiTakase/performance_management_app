package com.app.domain;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TRN_rep_assigning {

    private Integer rep_id;
    
    private Integer shop_id;
    
    private Integer start_month_id;
   
   	private String rep_assigning_status;

    @NotBlank
    private String author;

    @NotBlank
    private Date creation_date;

    private String updater;

    private Date update_date;

    @NotBlank
    private Integer deletion_flag;

}
