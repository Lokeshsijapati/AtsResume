package com.example.Ats.service;

import com.example.Ats.dto.ResumeAiSuggestionResponse;
import com.example.Ats.model.*;
import com.example.Ats.repository.ResumeRepository;
import com.example.Ats.util.ResumeAiPromptBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeAiSuggestionServiceImpl {

    private final ResumeRepository resumeRepository;
    private final GroqService groqService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    // private final ObjectMapper objectMapper;

    public ResumeAiSuggestionResponse generateSuggestions(Long resumeId) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        String resumeContent = buildResumeContent(resume);

        String prompt = ResumeAiPromptBuilder.build(resumeContent);

        String groqResponse = groqService.generateResponse(prompt);
        // System.out.println("GROQ RESPONSE = " + groqResponse);
        String aiContent = null;
        try {

            JsonNode root = objectMapper.readTree(groqResponse);

            aiContent = root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

            aiContent = aiContent
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            return objectMapper.readValue(
                    aiContent,
                    ResumeAiSuggestionResponse.class);

        } catch (Exception e) {

            // System.out.println("AI CONTENT = ");
            // System.out.println(aiContent);

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to parse AI response",
                    e);
        }
    }

    private String buildResumeContent(Resume resume) {

        StringBuilder builder = new StringBuilder();

        builder.append("Name: ")
                .append(resume.getName())
                .append("\n\n");

        builder.append("Career Objective: ")
                .append(resume.getCareerObjective())
                .append("\n\n");

        builder.append("Skills:\n");

        if (resume.getSkillList() != null) {

            for (Skill skill : resume.getSkillList()) {

                builder.append("- ")
                        .append(skill.getSkillName())
                        .append("\n");
            }
        }

        builder.append("\nEducation:\n");

        if (resume.getEducationList() != null) {

            for (Education education : resume.getEducationList()) {

                builder.append("- ")
                        .append(education.getLevel())
                        .append(" | ")
                        .append(education.getInstitution())
                        .append(" | ")
                        .append(education.getStartingYear())
                        .append(" - ")
                        .append(education.getEndingYear())
                        .append(" | ")
                        .append(education.getPercentageOrCgpa())
                        .append("\n");
            }
        }

        builder.append("\nProjects:\n");

        if (resume.getProjects() != null) {

            for (Project project : resume.getProjects()) {

                builder.append("Title: ")
                        .append(project.getProjectTitle())
                        .append("\n");

                builder.append("Description: ")
                        .append(project.getProjectDescription())
                        .append("\n");

                builder.append("Github: ")
                        .append(project.getGithubLink())
                        .append("\n\n");
            }
        }

        builder.append("\nWork Experience:\n");

        if (resume.getWorkExperience() != null) {

            for (WorkExperience experience : resume.getWorkExperience()) {

                builder.append("Company: ")
                        .append(experience.getCompanyName())
                        .append("\n");

                builder.append("Position: ")
                        .append(experience.getPosition())
                        .append("\n");

                builder.append("Duration: ")
                        .append(experience.getStartDate())
                        .append(" - ")
                        .append(experience.getEndDate())
                        .append("\n");

                builder.append("Description: ")
                        .append(experience.getDescription())
                        .append("\n\n");
            }
        }

        builder.append("\nCertifications:\n");

        if (resume.getCertifications() != null) {

            for (Certification certification : resume.getCertifications()) {

                builder.append("- ")
                        .append(certification.getCertificationName())
                        .append(" | ")
                        .append(certification.getOrganization())
                        .append("\n");
            }
        }

        builder.append("\nLanguages:\n");

        if (resume.getLanguages() != null) {

            for (Language language : resume.getLanguages()) {

                builder.append("- ")
                        .append(language.getLanguageName())
                        .append(" (")
                        .append(language.getProficiencyLevel())
                        .append(")")
                        .append("\n");
            }
        }

        builder.append("\nAchievements:\n");

        if (resume.getAchievements() != null) {

            builder.append(resume.getAchievements());
        }

        return builder.toString();
    }
}