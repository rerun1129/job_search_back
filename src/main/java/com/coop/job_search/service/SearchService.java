package com.coop.job_search.service;

import com.coop.job_search.domain.JobSearch;
import com.coop.job_search.domain.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public List<JobSearch> findJob() {

        return null;

    }



}
