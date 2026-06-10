package com.example.Ats.controller;

import com.example.Ats.dto.AnalysisResultDto;
import com.example.Ats.service.AIResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIResumeService aiResumeService;

    public AIController(AIResumeService aiResumeService) {
        this.aiResumeService = aiResumeService;
    }

    @PostMapping("/analyze-resume")
    public ResponseEntity<AnalysisResultDto> analyzeResume(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("jobDescription") String jobDescription
    ) throws Exception {

        AnalysisResultDto response = aiResumeService.analyzeResume(resume, jobDescription);

        return ResponseEntity.ok(response);
    }
}