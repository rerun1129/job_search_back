package com.coop.job_search.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobResponseDto {

    private String title;
    private String url;
    private Integer foundingDate;
    private Long revenue;
    private Integer employee;
    private String companyName;

    public JobResponseDto() {
    }

    @QueryProjection
    public JobResponseDto(Integer foundingDate, Long revenue, Integer employee, String title, String companyName, String url) {
        this.foundingDate = foundingDate;
        this.revenue = revenue;
        this.employee = employee;
        this.title = title;
        this.companyName = companyName;
        this.url = url;
    }
}
