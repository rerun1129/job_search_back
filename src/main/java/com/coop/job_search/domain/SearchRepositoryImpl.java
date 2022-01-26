package com.coop.job_search.domain;


import com.coop.job_search.dto.JobResponseDto;
import com.coop.job_search.dto.QJobResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

import static com.coop.job_search.domain.QJobSearch.jobSearch;


@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<JobResponseDto> searchPaging(Pageable pageable) {

        QueryResults<JobResponseDto> results = queryFactory
                .select(new QJobResponseDto(
                        jobSearch.foundingDate,
                        jobSearch.revenue,
                        jobSearch.employee,
                        jobSearch.title,
                        jobSearch.companyName,
                        jobSearch.url
                ))
                .from(jobSearch)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<JobResponseDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<JobResponseDto> filteredSearch(HashMap<String, Object> map) {

        Integer foundingDate = (Integer) map.get("foundingDate");
        Long revenue = Long.valueOf(String.valueOf(map.get("revenue")));
        Integer employee = (Integer) map.get("employee");

        List<JobResponseDto> results = queryFactory
                .select(new QJobResponseDto(
                        jobSearch.foundingDate,         //설립일 필터
                        jobSearch.revenue,              //매출액 필터
                        jobSearch.employee,             //종업원 필터
                        jobSearch.title,
                        jobSearch.companyName,
                        jobSearch.url
                ))
                .from(jobSearch)
                .where(foundingDateGoe(foundingDate), employeeGoe(employee), revenueGoe(revenue))
                .fetch();

        return results;
    }


    private BooleanExpression foundingDateGoe(Integer foundingDate) {
        return foundingDate == -1 ? null : jobSearch.foundingDate.goe(foundingDate);
    }

    private BooleanExpression revenueGoe(Long revenue) {
        return revenue == -1 ? null : jobSearch.revenue.goe(revenue);
    }

    private BooleanExpression employeeGoe(Integer employee) {
        return employee == -1 ? null : jobSearch.employee.goe(employee);
    }




}
