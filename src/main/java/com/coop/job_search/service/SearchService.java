package com.coop.job_search.service;

import com.coop.job_search.domain.SearchRepository;
import com.coop.job_search.dto.JobResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;


    public Page<JobResponseDto> findSearch(Pageable pageable) {
        return searchRepository.searchPaging(pageable);
    }

    public List<JobResponseDto> filteredSearch(HashMap<String, Object> map) {
        return searchRepository.filteredSearch(map);
    }

}
