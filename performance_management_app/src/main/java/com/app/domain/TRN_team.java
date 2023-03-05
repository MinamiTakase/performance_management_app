package com.app.domain;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TRN_team {
	@NotBlank
	private Integer team_id;
	
	@NotBlank
	private String team_name;
	
	@NotBlank
	private Date team_start_date;
	
	private Date team_end_date;
	
	private String team_status;
	
	@NotBlank
    private String author;

    @NotBlank
    private Date creation_date;

    private String updater;

    private Date update_date;

    @NotBlank
    private Integer deletion_flag;

}
