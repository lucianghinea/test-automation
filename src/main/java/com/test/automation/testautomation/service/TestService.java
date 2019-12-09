package com.test.automation.testautomation.service;

import com.test.automation.testautomation.domain.Test;
import com.test.automation.testautomation.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public Test save(Test test) {

        if (test.getDescription() == null || test.getDescription().equals("")) {
            test.setDescription("TO_DO");
        }

        return testRepository.save(test);
    }

    public Iterable<Test> findAll() {
        return testRepository.findAll();
    }

    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    public Optional<Test> findByDescription(String description) {
        return testRepository.findByDescription(description);
    }

    public void deleteTestById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        test.ifPresent(testRepository::delete);
    }
}
