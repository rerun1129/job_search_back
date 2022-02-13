package com.coop.job_search.controller;

import com.coop.job_search.dto.JobResponseDto;
import com.coop.job_search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/")
    public List<JobResponseDto> findSearch() {
        return searchService.findSearch();
    }

    @PostMapping("/search")
    public List<JobResponseDto> filteredSearch(@RequestBody HashMap<String, String> param) {
        List<JobResponseDto> resultMap;
        resultMap = searchService.filteredSearch(param);
        return resultMap;
    }
}
