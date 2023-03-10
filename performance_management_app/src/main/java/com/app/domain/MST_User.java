package com.app.domain;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MST_User {

    @NotBlank
    private Integer user_id;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    @NotBlank
    public String user_address;

    @NotBlank
    private String user_type;

    @NotBlank
    private String author;

    @NotBlank
    private Date creation_date;

    private String updater;

    private Date update_date;

    @NotBlank
    private Integer deletion_flag;

}
