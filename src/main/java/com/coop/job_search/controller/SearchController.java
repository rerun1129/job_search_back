package com.coop.job_search.controller;

import com.coop.job_search.dto.JobRequestDto;
import com.coop.job_search.dto.JobResponseDto;
import com.coop.job_search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")  //데이터는 Json 으로 받을 것이기 때문에 결과를 바로 화면에 내려주고 프론트에서 쿼리 스트링을 바꾸게 한다.
    public Page<JobResponseDto> findSearch(@RequestBody JobRequestDto requestDto, Pageable pageable) {
        return searchService.findSimpleJob(requestDto, pageable);
    }


}
