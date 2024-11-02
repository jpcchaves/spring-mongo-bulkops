package com.mongobulk.bulkops.controller;

import com.mongobulk.bulkops.service.ExampleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/save")
    public ResponseEntity<String> save() {

        return ResponseEntity.ok(exampleService.getSaveMetric());
    }

    @GetMapping("/save-bulk")
    public ResponseEntity<String> saveBulk() {

        return ResponseEntity.ok(exampleService.getSaveBulkMetric());
    }
}