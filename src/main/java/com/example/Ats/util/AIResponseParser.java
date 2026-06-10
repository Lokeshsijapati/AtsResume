package com.example.Ats.util;

import com.example.Ats.dto.AnalysisResultDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AIResponseParser {

    public AnalysisResultDto parseResponse(String response) {

        AnalysisResultDto dto = new AnalysisResultDto();

        List<String> matched = new ArrayList<>();
        List<String> missingKeywords = new ArrayList<>();
        List<String> recommendedSkills = new ArrayList<>();
        List<String> missingSections = new ArrayList<>();
        List<String> strengths = new ArrayList<>();
        List<String> weaknesses = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();

        String[] lines = response.split("\\r?\\n");

        String section = "";

        for (String line : lines) {

            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            String lower = line.toLowerCase();

            // SCORES

            if (lower.contains("resume match percentage")) {
                dto.setResumeMatchPercentage(extractNumber(line));
            }

            else if (lower.contains("ats compatibility score")) {
                dto.setAtsScore(extractNumber(line));
            }

            // SECTIONS

            else if (lower.contains("matched skills")) {
                section = "matched";
            }

            else if (lower.contains("missing keywords")) {
                section = "missing";
            }

            else if (lower.contains("recommended skills")) {
                section = "recommended";
            }

            else if (lower.contains("missing resume sections")) {
                section = "missingSections";
            }

            else if (lower.contains("resume strengths")) {
                section = "strengths";
            }

            else if (lower.contains("resume weaknesses")) {
                section = "weaknesses";
            }

            else if (lower.contains("improvement suggestions")) {
                section = "suggestions";
            }

            else if (lower.contains("summary")) {
                section = "summary";
            }

            // CONTENT

            else {

                line = line.replaceFirst("^-", "").trim();

                switch (section) {

                    case "matched" ->
                            matched.add(line);

                    case "missing" ->
                            missingKeywords.add(line);

                    case "recommended" ->
                            recommendedSkills.add(line);

                    case "missingSections" ->
                            missingSections.add(line);

                    case "strengths" ->
                            strengths.add(line);

                    case "weaknesses" ->
                            weaknesses.add(line);

                    case "suggestions" ->
                            suggestions.add(line);

                    case "summary" ->
                            dto.setSummary(
                                    dto.getSummary() == null
                                            ? line
                                            : dto.getSummary() + " " + line
                            );
                }
            }
        }

        dto.setMatchedSkills(matched);
        dto.setMissingKeywords(missingKeywords);
        dto.setRecommendedSkills(recommendedSkills);
        dto.setMissingSections(missingSections);
        dto.setStrengths(strengths);
        dto.setWeaknesses(weaknesses);
        dto.setSuggestions(suggestions);

        return dto;
    }

    private Integer extractNumber(String line) {

        try {
            return Integer.parseInt(
                    line.replaceAll("[^0-9]", "")
            );

        } catch (Exception e) {

            return 0;
        }
    }
}