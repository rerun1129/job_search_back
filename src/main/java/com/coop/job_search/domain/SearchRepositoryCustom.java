package com.coop.job_search.domain;

import com.coop.job_search.dto.JobResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface SearchRepositoryCustom {

    List<JobResponseDto> searchPaging();
    List<JobResponseDto> filteredSearch(HashMap<String , String> map);

}
