package com.test.automation.testautomation.repository;

import com.test.automation.testautomation.domain.WebElement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebElementRepository extends CrudRepository<WebElement, Long> {

    @Query("from WebElement")
    Optional<WebElement> findByName(String name);

    @Query("from WebElement as we inner join Page as p on we.page=lower(:id)")
    List<WebElement> findPageWebElements(@Param("id") Long pageId);

    @Query("from WebElement as we inner join Page as p on we.page=lower(:id) where we.name=lower(:name) ")
    WebElement findPageWebElement(@Param("id") Long pageId, @Param("name") String name);
}
