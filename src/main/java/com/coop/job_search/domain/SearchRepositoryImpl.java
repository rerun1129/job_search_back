package com.coop.job_search.domain;


import com.coop.job_search.dto.JobResponseDto;
import com.coop.job_search.dto.QJobResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.coop.job_search.domain.QJobSearch.jobSearch;

@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<JobResponseDto> searchPaging(String title, Pageable pageable) {

        QueryResults<JobResponseDto> results = queryFactory
                .select(new QJobResponseDto(
                        jobSearch.est,
                        jobSearch.revenue,
                        jobSearch.worker,
                        jobSearch.title,
                        jobSearch.comName
                ))
                .from(jobSearch)
                .where(jobSearch.title.eq(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<JobResponseDto> content = results.getResults();

        long total = results.getTotal();


        return new PageImpl<>(content, pageable, total);

    }
}
