package com.coop.job_search.controller;

import com.coop.job_search.domain.JobSearch;
import com.coop.job_search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService service;

    @GetMapping("/search")  //데이터는 Json 으로 받을 것이기 때문에 결과를 바로 화면에 내려주고 프론트에서 쿼리 스트링을 바꾸게 한다.
    public JobSearch findSearch() {




        return new JobSearch();
    }


}
