package com.example.Ats.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeAiSuggestionResponse {

    private List<String> careerObjectiveSuggestions;

    private List<String> projectDescriptionSuggestions;

    private List<String> experienceDescriptionSuggestions;

    private List<String> grammarImprovements;

    private List<String> professionalWordingSuggestions;

    private List<String> betterResumeSentences;

    private List<MissingSectionSuggestion> missingImportantSections;
}