package com.coop.job_search.domain;

import com.coop.job_search.dto.JobRequestDto;
import com.coop.job_search.dto.JobResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepositoryCustom {

    Page<JobResponseDto> searchPaging(String title, Pageable pageable);


}
