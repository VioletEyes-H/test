package com.bula.gzh.Repository;

import com.bula.gzh.Entity.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InfoRepository extends PagingAndSortingRepository<Info, Integer> {
    @Query("SELECT i FROM Info i WHERE i.endtime>=CURRENT_TIMESTAMP")
    Page<Info> findByBeforeEndTime(Pageable pageable);

    Page<Info> findByTitleLike(String title, Pageable pageable);

    Info findById(int id);
}
