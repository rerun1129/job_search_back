package com.coop.job_search.domain;

import com.coop.job_search.dto.JobResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<JobSearch,Long>, SearchRepositoryCustom {

    //4개의 데이터를 받아서 조회해서 5개로 돌려주는 쿼리문이 필요함. Querydsl 동적 쿼리 필요
    //DTO 리포지토리를 하나 만들어서 프론트에서 DTO 타입 Json 파일로 데이터를 받아서 그걸로 동적 쿼리를 짠다.


}
