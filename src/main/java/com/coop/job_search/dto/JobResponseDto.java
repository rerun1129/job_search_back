package com.coop.job_search.dto;

import com.coop.job_search.domain.JobSearch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobResponseDto {


    /**
     * title 에 URL 이 필요하기 때문에 URL 크롤링 해와서 여기에 URL 필드 하나를 추가하고 프론트에 알려줘야 됨.
     */
    private LocalDateTime est;
    private Long revenue;
    private Integer worker;
    private String title;
    private String comName;

    @QueryProjection
    public JobResponseDto(LocalDateTime est, Long revenue, Integer worker, String title, String comName) {
        this.est = est;
        this.revenue = revenue;
        this.worker = worker;
        this.title = title;
        this.comName = comName;
    }
}
