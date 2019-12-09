package com.test.automation.testautomation.repository;

import com.test.automation.testautomation.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends CrudRepository<Page, Long> {

//    @Query("from Page as p where p.name=lower(:name)")
    Page findByName(String name);
}
