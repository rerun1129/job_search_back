package com.coop.job_search.domain;


import com.coop.job_search.dto.JobResponseDto;
import com.coop.job_search.dto.QJobResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
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

        Integer foundingDate = 0, employee = 0;
        String foundingDateBetween = "", employeeBetween = "", revenueBetween = "";
        long revenue = 0;

        if ( map.get("foundingDate") instanceof Integer ){
            foundingDate = (Integer) map.get("foundingDate");
        } else {
            foundingDateBetween = String.valueOf(map.get("foundingDate"));
        }

        if ( map.get("employee") instanceof Integer ){
            employee = (Integer) map.get("employee");
        } else {
            employeeBetween = String.valueOf(map.get("employee"));
        }

        if ( map.get("revenue") instanceof Long ){
            revenue = Long.parseLong(String.valueOf(map.get("revenue")));
        } else {
            revenueBetween = String.valueOf(map.get("revenue"));
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
                .where(   goeComparator(foundingDate, jobSearch.foundingDate)
                        , goeComparator(employee, jobSearch.employee)
                        , goeComparator(revenue, jobSearch.revenue)
                        , betweenComparator(foundingDateBetween, jobSearch.foundingDate, 0, 5)
                        , betweenComparator(employeeBetween, jobSearch.employee, 0 ,10)
                        , betweenComparator(revenueBetween, jobSearch.revenue, 0, 1000000000))
                .fetch();
        return results;
    }


    private <T extends Number & Comparable<?>> BooleanExpression goeComparator (T condition, NumberPath<T> filtered){
        return condition.equals(-1) ? null : filtered.goe(condition);
    }

    private <T extends Number & Comparable<?>> BooleanExpression betweenComparator (String condition, NumberPath<T> filtered, Integer from, Integer to){
        return condition.equals("between") ? filtered.between(from, to) : null;
    }
}
