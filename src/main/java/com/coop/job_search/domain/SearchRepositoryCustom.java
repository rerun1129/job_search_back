package com.coop.job_search.domain;

import com.coop.job_search.dto.JobRequestDto;
import com.coop.job_search.dto.JobResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface SearchRepositoryCustom {

    Page<JobResponseDto> searchPaging(Pageable pageable);

    List<JobResponseDto> filteredSearch(HashMap<String , Object> map);


}
