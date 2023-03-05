package com.app.domain;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TRN_rep {
	
	@NotBlank
	private Integer rep_id;
	
	@NotBlank
	private String rep_name;
	
	@NotBlank
	private Integer user_id;
	
	@NotBlank
	private Date start_date;
	
	private String rep_status;
	
	@NotBlank
    private String author;

    @NotBlank
    private Date creation_date;

    private String updater;

    private Date update_date;

    @NotBlank
    private Integer deletion_flag;

}
