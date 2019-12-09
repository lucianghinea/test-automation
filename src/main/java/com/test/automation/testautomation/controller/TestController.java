package com.test.automation.testautomation.controller;

import com.test.automation.testautomation.domain.Test;
import com.test.automation.testautomation.service.TestService;
import com.test.automation.testautomation.validation.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/test")
    public ResponseEntity<?> addTest(@RequestBody Test test, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Test newTest = testService.save(test);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(newTest.getId()).toUri();

        return ResponseEntity.created(location).body(newTest);
    }

    @GetMapping("/tests")
    public ResponseEntity<Iterable<Test>> getAll() {
        return ResponseEntity.ok(testService.findAll());
    }

    @GetMapping("/tests/id/{id}")
    public ResponseEntity<Optional<Test>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.findById(id));
    }

    @GetMapping("/tests/description/{description}")
    public ResponseEntity<Optional<Test>> getByDescription(@PathVariable String description) {
        return ResponseEntity.ok(testService.findByDescription(description));
    }
}
