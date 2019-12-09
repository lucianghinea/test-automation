package com.test.automation.testautomation.repository;

import com.test.automation.testautomation.domain.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {

    Optional<Test> findByDescription(String description);
}
