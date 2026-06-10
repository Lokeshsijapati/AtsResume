package com.example.Ats.Mapper;

import com.example.Ats.dto.*;
import com.example.Ats.model.*;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResumeMapper {

    // Convert entity to response dto
    public ResumeResponseDTO toDTO(Resume resume) {

        ResumeResponseDTO dto =
                new ResumeResponseDTO();

        // basic details
        dto.setId(resume.getId());

        dto.setTemplateName(
                resume.getTemplateName());

        dto.setName(
                resume.getName());

        dto.setEmail(
                resume.getEmail());

        dto.setPhone(
                resume.getPhone());

        dto.setAddress(
                resume.getAddress());

        dto.setLinkedin(
                resume.getLinkedin());

        dto.setGithub(
                resume.getGithub());

        dto.setCareerObjective(
                resume.getCareerObjective());

        dto.setAchievements(
                resume.getAchievements());

        // education details
        if (resume.getEducationList() != null) {

            List<EducationDto> educationDtos =
                    resume.getEducationList()
                            .stream()
                            .map(education -> {

                                EducationDto edto =
                                        new EducationDto();

                                edto.setLevel(
                                        education.getLevel());

                                edto.setInstitution(
                                        education.getInstitution());
                                // edto.setBoardOrUniversity(
                                //         education.getBoardOrUniversity());

                                edto.setStartingYear(
                                        education.getStartingYear());

                                edto.setEndingYear(
                                        education.getEndingYear());

                                edto.setPercentageOrCgpa(
                                        education.getPercentageOrCgpa());

                                return edto;
                            })
                            .collect(Collectors.toList());

            dto.setEducationList(
                    educationDtos);
        }

        // skills
        if (resume.getSkillList() != null) {

            List<SkillDto> skillDtos =
                    resume.getSkillList()
                            .stream()
                            .map(skill -> {

                                SkillDto sdto =
                                        new SkillDto();

                                sdto.setSkillName(
                                        skill.getSkillName());

                                return sdto;
                            })
                            .collect(Collectors.toList());

            dto.setSkillList(
                    skillDtos);
        }

        // projects
        if (resume.getProjects() != null) {

            List<ProjectDto> projectDtos =
                    resume.getProjects()
                            .stream()
                            .map(project -> {

                                ProjectDto pdto =
                                        new ProjectDto();

                                pdto.setProjectTitle(
                                        project.getProjectTitle());

                                pdto.setProjectDescription(
                                        project.getProjectDescription());

                                pdto.setGithubLink(
                                        project.getGithubLink());

                                return pdto;
                            })
                            .collect(Collectors.toList());

            dto.setProjects(
                    projectDtos);
        }

        // work experience
        if (resume.getWorkExperience() != null) {

            List<WorkExperienceDto> experienceDtos =
                    resume.getWorkExperience()
                            .stream()
                            .map(experience -> {

                                WorkExperienceDto wdto =
                                        new WorkExperienceDto();

                                wdto.setCompanyName(
                                        experience.getCompanyName());

                                wdto.setPosition(
                                        experience.getPosition());

                                wdto.setStartDate(
                                        experience.getStartDate());

                                wdto.setEndDate(
                                        experience.getEndDate());

                                wdto.setDescription(
                                        experience.getDescription());

                                return wdto;
                            })
                            .collect(Collectors.toList());

            dto.setWorkExperience(
                    experienceDtos);
        }

        // certifications
        if (resume.getCertifications() != null) {

            List<CertificationDto> certificationDtos =
                    resume.getCertifications()
                            .stream()
                            .map(certification -> {

                                CertificationDto cdto =
                                        new CertificationDto();

                                cdto.setCertificationName(
                                        certification.getCertificationName());

                                cdto.setOrganization(
                                        certification.getOrganization());

                                cdto.setIssueDate(
                                        certification.getIssueDate());

                                cdto.setExpirationDate(
                                        certification.getExpirationDate());

                                return cdto;
                            })
                            .collect(Collectors.toList());

            dto.setCertifications(
                    certificationDtos);
        }

        // languages
        if (resume.getLanguages() != null) {

            List<LanguageDto> languageDtos =
                    resume.getLanguages()
                            .stream()
                            .map(language -> {

                                LanguageDto ldto =
                                        new LanguageDto();

                                ldto.setLanguageName(
                                        language.getLanguageName());

                                ldto.setProficiencyLevel(
                                        language.getProficiencyLevel());

                                return ldto;
                            })
                            .collect(Collectors.toList());

            dto.setLanguages(
                    languageDtos);
        }

        return dto;
    }
}