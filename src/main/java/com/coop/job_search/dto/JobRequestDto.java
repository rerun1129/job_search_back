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

    private LocalDateTime est;
    private Long revenue;
    private Integer worker;
    private String title;


    @Builder
    public JobRequestDto(LocalDateTime est, Long revenue, Integer worker, String title) {
        this.est = LocalDateTime.parse(est.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        this.revenue = revenue;
        this.worker = worker;
        this.title = title;
    }


    public JobSearch toEntity() {
        return JobSearch.builder()
                .est(est)
                .revenue(revenue)
                .worker(worker)
                .title(title)
                .build();
    }

}
