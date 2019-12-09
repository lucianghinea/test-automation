package com.test.automation.testautomation.service;

import com.test.automation.testautomation.domain.Page;
import com.test.automation.testautomation.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    public Page save(Page page) {

        if (page.getName() == null || page.getName().equals("")) {
            page.setName("TO_DO");
        }

        return pageRepository.save(page);
    }

    public Iterable<Page> findAll() {
        return pageRepository.findAll();
    }

    public Optional<Page> findById(Long id) {
        return pageRepository.findById(id);
    }

    public Page findByName(String name) {
        return pageRepository.findByName(name);
    }

    public void deleteById(Long id) {
        Optional<Page> pageDeleted = pageRepository.findById(id);
        pageDeleted.ifPresent(pageRepository::delete);
    }
}
