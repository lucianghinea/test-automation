package com.test.automation.testautomation.controller;

import com.test.automation.testautomation.domain.Page;
import com.test.automation.testautomation.service.PageService;
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
public class PageController {

    @Autowired
    private PageService pageService;

    @PostMapping("/page")
    public ResponseEntity<?> addPage(@Valid @RequestBody Page page, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Page newPage = pageService.save(page);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPage.getId()).toUri();

        return ResponseEntity.created(location).body(newPage);
    }

    @GetMapping("/pages")
    public ResponseEntity<Iterable<Page>> getAllPages() {
        return ResponseEntity.ok(pageService.findAll());
    }

    @GetMapping("/pages/id/{id}")
    public ResponseEntity<Optional<Page>> findPageById(@PathVariable Long id) {
        return ResponseEntity.ok(pageService.findById(id));
    }

    @GetMapping("/pages/name/{name}")
    public Page findByName(@PathVariable String name) {
        return pageService.findByName(name);
    }

    @DeleteMapping("/page/id/{id}")
    public ResponseEntity<Page> deletePageById(@PathVariable Long id) {
        pageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
