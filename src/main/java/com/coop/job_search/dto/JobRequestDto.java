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

    private Integer est;
    private String revenue;
    private Integer worker;


    @Builder
    public JobRequestDto(Integer est, String revenue, Integer worker) {
        this.est = est;
        this.revenue = revenue;
        this.worker = worker;
    }


    public JobSearch toEntity() {
        return JobSearch.builder()
                .est(est)
                .revenue(revenue)
                .workers(worker)
                .build();
    }

}
