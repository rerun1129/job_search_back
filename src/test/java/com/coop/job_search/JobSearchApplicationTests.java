package com.coop.job_search;

import com.coop.job_search.domain.JobSearch;
import com.coop.job_search.domain.QJobSearch;
import com.coop.job_search.domain.SearchRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.coop.job_search.domain.QJobSearch.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JobSearchApplicationTests {

    /**
     * 프론트 엔드가 어느정도 완성되면 필터링 조건 별로 테스트 코드 작성해야 함.
     */

    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;


    @Autowired
    SearchRepository searchRepository;

    @BeforeEach
    public void init() {

        queryFactory = new JPAQueryFactory(em);

        searchRepository.save(new JobSearch("구인A", "인력사무소A", LocalDateTime.of(2022,1,1,12,0),50000000000L,100));
        searchRepository.save(new JobSearch("구인B", "인력사무소B", LocalDateTime.of(2022,1,1,13,0),400000000L,60));
        searchRepository.save(new JobSearch("구인C", "인력사무소C", LocalDateTime.of(2022,1,1,14,0),30000000L,700));
        searchRepository.save(new JobSearch("구인D", "인력사무소D", LocalDateTime.of(2022,1,1,15,0),2000000000L,500));
    }


    @Test
    @DisplayName("도메인과 저장소 테스트")
    public void testRepo() throws Exception{

        //when

        List<JobSearch> list = searchRepository.findAll();
        JobSearch searchTitle = searchRepository.findByTitle("구인A");

        //then

        assertThat(list.size()).isEqualTo(4);
        assertThat(list.get(3).getEst()).isAfter(LocalDateTime.of(2022,1,1,11,0));
        assertThat(searchTitle.getTitle()).isEqualTo("구인A");
        assertThat(searchTitle.getCreateTime()).isAfter(LocalDateTime.of(2022,1,1,12,0));

    }

    @Test
    @DisplayName("기본적인 CRUD 테스트")
    public void CRUDTest() throws Exception{

        //C
        JobSearch savedResult = searchRepository.save(new JobSearch("구인E", "인력사무소E", LocalDateTime.now(), 50000000000L, 99));
        //R
        Optional<JobSearch> searchId = searchRepository.findById(4L);


        assertThat(savedResult.getTitle()).isEqualTo("구인E");
        assertThat(searchId.get().getComName()).isEqualTo("인력사무소D");


    }

    @Test
    @DisplayName("큐타입 테스트")
    public void qtypeTest() throws Exception{

        JobSearch findJob = queryFactory
                .select(jobSearch)
                .from(jobSearch)
                .where(jobSearch.comName.eq("인력사무소C"))
                .fetchOne();

        assertThat(findJob.getTitle()).isEqualTo("구인C");



    }




}
