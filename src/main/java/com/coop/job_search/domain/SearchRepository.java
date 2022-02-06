package com.coop.job_search.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<JobSearch,Long>, SearchRepositoryCustom {

}
