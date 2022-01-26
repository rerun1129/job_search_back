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
    private Integer foundingDate;
    //매출액
    private Long revenue;
    //사원수
    private Integer employee;
    //회사 구분
    private String size;
    //회사 명칭
    private String companyName;

    @Builder
    public JobSearch(String title, String url, String corp_name, Integer foundingDate, Long revenue, Integer employee, String size, String companyName) {
        this.title = title;
        this.url = url;
        this.corp_name = corp_name;
        this.foundingDate = foundingDate;
        this.revenue = revenue;
        this.employee = employee;
        this.size = size;
        this.companyName = companyName;
    }

    public JobSearch(String title, String url, Integer foundingDate, Long revenue, Integer employee, String companyName) {
        this.title = title;
        this.url = url;
        this.foundingDate = foundingDate;
        this.revenue = revenue;
        this.employee = employee;
        this.companyName = companyName;
    }
}
