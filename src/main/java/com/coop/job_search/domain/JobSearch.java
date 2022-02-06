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
    private String title;
    private String url;
    private String corp_name;
    private Integer foundingDate;
    private Long revenue;
    private Integer employee;
    private String size;
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
