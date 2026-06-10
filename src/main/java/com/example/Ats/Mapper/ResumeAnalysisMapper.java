package com.example.Ats.Mapper;

import com.example.Ats.dto.AnalysisResultDto;
import com.example.Ats.model.ResumeAnalysis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeAnalysisMapper {

        public static AnalysisResultDto toDto(
                        ResumeAnalysis analysis) {

                AnalysisResultDto dto = new AnalysisResultDto();

                dto.setResumeMatchPercentage(
                                analysis.getResumeMatchPercentage());

                dto.setAtsScore(
                                analysis.getAtsScore());

                dto.setMatchedSkills(
                                convertToList(
                                                analysis.getMatchedSkills()));

                dto.setMissingKeywords(
                                convertToList(
                                                analysis.getMissingKeywords()));

                dto.setRecommendedSkills(
                                convertToList(
                                                analysis.getRecommendedSkills()));

                dto.setStrengths(
                                convertToList(
                                                analysis.getStrengths()));

                dto.setWeaknesses(
                                convertToList(
                                                analysis.getWeaknesses()));

                dto.setSuggestions(
                                convertToList(
                                                analysis.getSuggestions()));

                dto.setSummary(
                                analysis.getSummary());

                // dto.setFullResponse(
                //                 analysis.getFullResponse());

                dto.setCreatedAt(
                                analysis.getCreatedAt());

                return dto;
        }

        private static List<String> convertToList(
                        String value) {

                if (value == null || value.isBlank()) {
                        return List.of();
                }

                return Arrays.stream(
                                value.split("\\n"))
                                .map(String::trim)
                                .filter(line -> !line.isEmpty())
                                .map(line -> line.replaceFirst("^-", "").trim())
                                .collect(Collectors.toList());
        }
}