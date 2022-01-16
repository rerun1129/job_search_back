package com.coop.job_search.controller;

import com.coop.job_search.dto.JobResponseDto;
import com.coop.job_search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;



    @GetMapping("/")
    public Page<JobResponseDto> findSearch(Pageable pageable) {
        return searchService.findSearch(pageable);
    }


}
