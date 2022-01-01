package com.coop.job_search.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class JobSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id")
    private Long id;
    //링크 제목
    private String title;
    //회사이름
    private String comName;
    //설립일
    private LocalDateTime est;
    //매출액
    private Long revenue;
    //사원수
    private Integer worker;
    //해당 데이터의 크롤링 일자
    @Column(updatable = false)
    private LocalDateTime createTime;

    @Builder
    public JobSearch(String title, String comName, LocalDateTime est, Long revenue, Integer worker) {
        this.title = title;
        this.comName = comName;
        this.est = est;
        this.revenue = revenue;
        this.worker = worker;
        this.createTime = LocalDateTime.now();
    }

}
