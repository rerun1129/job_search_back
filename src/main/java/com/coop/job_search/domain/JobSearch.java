package com.coop.job_search.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class JobSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //링크 제목
    private String title;
    //url
    private String url;
    //회사 csn
    private String corp_name;
    //업력
    private Integer est;
    //매출액
    private String revenue;
    //사원수
    private Integer workers;
    //회사 구분
    private String size;
    //회사 명칭
    private String real_name;

    @Builder
    public JobSearch(String title, String url, String corp_name, Integer est, String revenue, Integer workers, String size, String real_name) {
        this.title = title;
        this.url = url;
        this.corp_name = corp_name;
        this.est = est;
        this.revenue = revenue;
        this.workers = workers;
        this.size = size;
        this.real_name = real_name;
    }

    public JobSearch(String title, String url, Integer est, String revenue, Integer workers, String real_name) {
        this.title = title;
        this.url = url;
        this.est = est;
        this.revenue = revenue;
        this.workers = workers;
        this.real_name = real_name;
    }
}
