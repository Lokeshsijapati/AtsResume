package com.example.Ats.service;

import com.example.Ats.dto.AnalysisResultDto;
import com.example.Ats.model.ResumeAnalysis;
import com.example.Ats.repository.ResumeAnalysisRepository;
import com.example.Ats.util.AIResponseParser;
import com.example.Ats.util.PdfUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class AIResumeService {

        private final GroqService groqService;
        private final ResumeAnalysisRepository repository;
        private final AIResponseParser parser;

        public AIResumeService(
                        GroqService groqService,
                        ResumeAnalysisRepository repository,
                        AIResponseParser parser) {

                this.groqService = groqService;
                this.repository = repository;
                this.parser = parser;
        }

        public AnalysisResultDto analyzeResume(
                        MultipartFile resume,
                        String jobDescription) throws IOException {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth == null || !auth.isAuthenticated()) {
                        throw new RuntimeException("User not authenticated");
                }

                String userEmail = auth.getName();

                String extractedResume;

                try {
                        extractedResume = PdfUtil.extractText(resume);
                        if (extractedResume.length() > 8000) {
                                extractedResume = extractedResume.substring(0, 8000);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to extract resume text");
                }

                System.out.println("Extracted Resume: " + extractedResume);

                String prompt = """
                                Analyze the following resume against the provided job description.

                                Evaluate the resume based on:

                                1. Resume Match Percentage
                                2. ATS Compatibility Score
                                3. Improvement Suggestions
                                4. Missing Keywords
                                5. Recommended Skills
                                6. Resume Strength & Weakness Report
                                7. Missing Resume Sections
                                8. Never miss Ats Score and Resume Match Percentage in your response, they are crucial for our analysis

                                Resume:
                                %s

                                Job Description:
                                %s

                                Instructions:
                                1. Identify all matched technical and soft skills from the resume
                                2. Identify all important missing or weak skills from the job description
                                3. Calculate:
                                - Resume Match Percentage (0-100)
                                - ATS Compatibility Score (0-100)
                                4. Identify missing ATS keywords
                                5. Recommend additional relevant skills
                                6. Provide strengths and weaknesses of the resume
                                7. Detect only these important missing resume sections:
                                - Skills
                                - Experience
                                - Education
                                - Projects
                                8. If important sections are missing, mention that they may slightly reduce ATS score
                                9. Give practical and professional improvement suggestions
                                10. Keep the analysis concise, accurate, and recruiter-focused

                                Return response ONLY in this exact format:

                                Resume Match Percentage: <number>

                                ATS Compatibility Score: <number>

                                Matched Skills:
                                - List all relevant matched skills

                                Missing Keywords:
                                - List important missing ATS keywords

                                Recommended Skills:
                                - List recommended skills to improve resume relevance

                                Missing Resume Sections:
                                - List important missing resume sections

                                Resume Strengths:
                                - List key strengths

                                Resume Weaknesses:
                                - List key weaknesses

                                Improvement Suggestions:
                                - List practical improvement suggestions

                                Summary:
                                <short professional summary>
                                """
                                .formatted(extractedResume, jobDescription);

                String aiResponse = groqService.generateResponse(prompt);

                // PARSE RESPONSE

                String content = extractContent(aiResponse);

                // System.out.println(content);

                AnalysisResultDto dto = parser.parseResponse(content);

                // ATS SCORE ADJUSTMENT

                int atsScore = dto.getAtsScore();
                System.out.println("your Score" + atsScore);
                for (String section : dto.getMissingSections()) {

                        String s = section.toLowerCase();

                        if (s.contains("skills")) {
                                atsScore -= 15;
                        }

                        if (s.contains("experience")) {
                                atsScore -= 10;
                        }

                        if (s.contains("education")) {
                                atsScore -= 5;
                        }

                        if (s.contains("projects")) {
                                atsScore -= 5;
                        }
                }

                // safe negative score

                dto.setAtsScore(Math.max(0, atsScore));

                dto.setCreatedAt(LocalDateTime.now());

                // SAVE TO DB

                ResumeAnalysis entity = new ResumeAnalysis();

                entity.setUserEmail(userEmail);

                entity.setResumeMatchPercentage(
                                dto.getResumeMatchPercentage());

                entity.setAtsScore(dto.getAtsScore());

                entity.setMatchedSkills(
                                String.join("\n", dto.getMatchedSkills()));

                entity.setMissingKeywords(
                                String.join("\n", dto.getMissingKeywords()));

                entity.setRecommendedSkills(
                                String.join("\n", dto.getRecommendedSkills()));

                entity.setMissingSections(
                                String.join("\n", dto.getMissingSections()));

                entity.setStrengths(
                                String.join("\n", dto.getStrengths()));

                entity.setWeaknesses(
                                String.join("\n", dto.getWeaknesses()));

                entity.setSuggestions(
                                String.join("\n", dto.getSuggestions()));

                entity.setSummary(dto.getSummary());

                entity.setCreatedAt(dto.getCreatedAt());

                repository.save(entity);

                return dto;
        }

        private String extractContent(String response) {

                try {

                        ObjectMapper mapper = new ObjectMapper();

                        JsonNode root = mapper.readTree(response);

                        return root
                                        .path("choices")
                                        .get(0)
                                        .path("message")
                                        .path("content")
                                        .asText();

                } catch (Exception e) {

                        return response;
                }
        }
}