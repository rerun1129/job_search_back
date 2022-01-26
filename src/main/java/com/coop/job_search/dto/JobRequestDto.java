package com.coop.job_search.dto;

import com.coop.job_search.domain.JobSearch;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class JobRequestDto {

    private Integer foundingDate;
    private Long revenue;
    private Integer employee;


    @Builder
    public JobRequestDto(Integer foundingDate, Long revenue, Integer employee) {
        this.foundingDate = foundingDate;
        this.revenue = revenue;
        this.employee = employee;
    }


    public JobSearch toEntity() {
        return JobSearch.builder()
                .foundingDate(foundingDate)
                .revenue(revenue)
                .employee(employee)
                .build();
    }

}
