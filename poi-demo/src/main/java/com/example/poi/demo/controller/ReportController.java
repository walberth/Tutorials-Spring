package com.example.poi.demo.controller;

import com.example.poi.demo.service.ReportService;
import com.example.poi.demo.transversal.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/report")
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<Resource> getReport() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, Constant.EXCEL_HEADER)
                .contentType(MediaType.parseMediaType(Constant.EXCEL_MEDIA_TYPE))
                .cacheControl(CacheControl.noCache())
                .body(reportService.getReport());
    }
}
