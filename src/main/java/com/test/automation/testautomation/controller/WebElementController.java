package com.test.automation.testautomation.controller;

import com.test.automation.testautomation.domain.WebElement;
import com.test.automation.testautomation.service.PageService;
import com.test.automation.testautomation.service.WebElementService;
import com.test.automation.testautomation.validation.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin
public class WebElementController {

    @Autowired
    private WebElementService webElementService;

    @Autowired
    private PageService pageService;

    @PostMapping("/webelement")
    public ResponseEntity<?> addPage(@Valid @RequestBody WebElement element, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        WebElement newWebElement = webElementService.save(element);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(newWebElement.getId()).toUri();

        return ResponseEntity.created(location).body(newWebElement);
    }

    @GetMapping("/webelements")
    public ResponseEntity<Iterable<WebElement>> getAll() {
        return ResponseEntity.ok(webElementService.findAll());
    }

    @GetMapping("/webelements/page/{name}")
    public ResponseEntity<Iterable<WebElement>> getPageWebElements(@PathVariable String name) {
        final Long pageId = pageService.findByName(name).getId();
        return ResponseEntity.ok(webElementService.findPageWebElements(pageId));
    }

    @GetMapping("/webelements/page/{id}/{name}")
    public ResponseEntity<WebElement> getPageWebElement(@PathVariable Long id, @PathVariable String name) {
        return ResponseEntity.ok(webElementService.findPageWebElement(id, name));
    }

    @GetMapping("/webelements/id/{id}")
    public ResponseEntity<Optional<WebElement>> getWebElementById(@PathVariable Long id) {
        return ResponseEntity.ok(webElementService.findWebElementById(id));
    }

    @GetMapping("/webelements/name/{name}")
    public ResponseEntity<Optional<WebElement>> getWebElementByName(@PathVariable String name) {
        return ResponseEntity.ok(webElementService.findtByName(name));
    }

    @DeleteMapping("/webelement/{id}")
    public ResponseEntity<WebElement> deleteWebElementById(@PathVariable Long id) {
        webElementService.deleteWebElementById(id);
        return ResponseEntity.noContent().build();
    }
}
