package com.example.Ats.controller;

import com.example.Ats.model.ResumeAnalysis;
import com.example.Ats.repository.ResumeAnalysisRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final ResumeAnalysisRepository repository;

    public DashboardController(
            ResumeAnalysisRepository repository
    ) {
        this.repository = repository;
    }

    @GetMapping("/stats")
    public List<ResumeAnalysis> getStats() {

        return repository.findAll();
    }
}