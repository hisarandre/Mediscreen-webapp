package com.mediscreen.webapp.beans;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryBean {

    private String id;

    private Integer patId;

    private String patient;

    private LocalDate creationDate;
    @NotBlank(message = "Notes is mandatory")
    private String notes;
}
