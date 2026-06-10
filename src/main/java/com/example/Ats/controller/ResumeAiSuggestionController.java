package com.example.Ats.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ats.dto.ResumeAiSuggestionResponse;
import com.example.Ats.service.ResumeAiSuggestionServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeAiSuggestionController {

    private final ResumeAiSuggestionServiceImpl resumeAiSuggestionService;

    @PostMapping("/{resumeId}/suggestions")
    public ResponseEntity<ResumeAiSuggestionResponse> getSuggestions(
            @PathVariable Long resumeId) {

        return ResponseEntity.ok(
                resumeAiSuggestionService.generateSuggestions(resumeId)
        );
    }
}