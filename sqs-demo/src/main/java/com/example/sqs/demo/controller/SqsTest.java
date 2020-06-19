package com.example.sqs.demo.controller;

import com.example.sqs.demo.service.SqsTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/sqs")
@RequiredArgsConstructor
public class SqsTest {
    private final SqsTestService sqsTestService;

    @GetMapping(path="/test/{message}")
    public Object testingSqs(@PathVariable("message") String message) {
        return sqsTestService.testingSqsService(message);
    }
}
