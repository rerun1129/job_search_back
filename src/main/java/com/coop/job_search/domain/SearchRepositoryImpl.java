package com.coop.job_search.domain;


import com.coop.job_search.dto.JobResponseDto;
import com.coop.job_search.dto.QJobResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

import static com.coop.job_search.domain.QJobSearch.jobSearch;


@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<JobResponseDto> searchPaging() {

        List<JobResponseDto> results = queryFactory
                .select(new QJobResponseDto(
                        jobSearch.foundingDate,
                        jobSearch.revenue,
                        jobSearch.employee,
                        jobSearch.title,
                        jobSearch.companyName,
                        jobSearch.url
                ))
                .from(jobSearch)
                .fetch();

        return results;
    }

    @Override
    public List<JobResponseDto> filteredSearch(HashMap<String, String> map) {

        String foundingDate = map.get("foundingDate");
        Integer foundingDateInt = 0;
        String employee = map.get("employee");
        Integer employeeInt = 0;
        String revenue = map.get("revenue");
        long revenueInt = 0;

        if(foundingDate.equals("between")){
           foundingDate = map.get("foundingDate"); //between
        }else {
            foundingDateInt = Integer.parseInt(map.get("foundingDate"));
        }

        if(employee.equals("between")){
            employee = map.get("employee"); //between
        }else {
            employeeInt = Integer.parseInt(map.get("employee"));
        }

        if(revenue.equals("between")){
            revenue = map.get("revenue"); //between
        }else {
            revenueInt = Long.parseLong(map.get("revenue"));
        }


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
                .where(   goeComparator(foundingDateInt, jobSearch.foundingDate)
                        , goeComparator(employeeInt, jobSearch.employee)
                        , goeComparator(revenueInt, jobSearch.revenue)
                        , betweenComparator(foundingDate, jobSearch.foundingDate, 0, 5)
                        , betweenComparator(employee, jobSearch.employee, 0 ,10)
                        , betweenComparator(revenue, jobSearch.revenue, 0, 1000000000))
                .fetch();
        return results;
    }


    private <T extends Number & Comparable<?>> BooleanExpression goeComparator (T condition, NumberPath<T> filtered){
        if(condition.equals(-1) || condition.equals(-1L)){
            return null;
        } else {
            return filtered.goe(condition);
        }
    }

    private <T extends Number & Comparable<?>> BooleanExpression betweenComparator (String condition, NumberPath<T> filtered, Integer from, Integer to){
        return condition.equals("between") ? filtered.between(from, to) : null;
    }
}
