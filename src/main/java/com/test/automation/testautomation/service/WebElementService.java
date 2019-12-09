package com.test.automation.testautomation.service;

import com.test.automation.testautomation.domain.WebElement;
import com.test.automation.testautomation.repository.WebElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebElementService {

    @Autowired
    private WebElementRepository webElementRepository;

    public WebElement save(WebElement element) {

        if (element.getName() == null || element.getName().equals("")) {
            element.setName("TO_DO");
        }

        return webElementRepository.save(element);
    }

    public Iterable<WebElement> findPageWebElements(Long id) {
        return webElementRepository.findPageWebElements(id);
    }

    public WebElement findPageWebElement(Long id, String name) {
        return webElementRepository.findPageWebElement(id, name);
    }

    public Iterable<WebElement> findAll() {
        return webElementRepository.findAll();
    }

    public Optional<WebElement> findWebElementById(Long id) {
        return webElementRepository.findById(id);
    }

    public Optional<WebElement> findtByName(String name) {
        return webElementRepository.findByName(name);
    }

    public void deleteWebElementById(Long id) {
        Optional<WebElement> element = webElementRepository.findById(id);
        element.ifPresent(webElementRepository::delete);
    }
}
