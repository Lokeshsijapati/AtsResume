package com.example.Ats.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AnalysisResultDto {

    private Integer resumeMatchPercentage;

    private Integer atsScore;

    private List<String> matchedSkills;

    private List<String> missingKeywords;

    private List<String> recommendedSkills;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> suggestions;

    private List<String> missingSections;
    private String summary;

    // private String fullResponse;

    private LocalDateTime createdAt;
}