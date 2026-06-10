package com.example.Ats.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResumeResponseDTO {

    // resume id
    private Long id;

    // selected template name
    private String templateName;

    // personal details
    private String name;

    private String email;

    private String phone;

    private String address;

    private String linkedin;

    private String github;

    private String careerObjective;

    // education details
    private List<EducationDto> educationList;

    // skills list
    private List<SkillDto> skillList;

    // project details
    private List<ProjectDto> projects;

    // work experience details
    private List<WorkExperienceDto> workExperience;

    // certifications list
    private List<CertificationDto> certifications;

    // achievements
    private String achievements;

    // known languages
    private List<LanguageDto> languages;
}