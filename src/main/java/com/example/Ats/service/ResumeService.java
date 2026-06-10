package com.example.Ats.service;

import com.example.Ats.Mapper.ResumeMapper;
import com.example.Ats.dto.*;
import com.example.Ats.model.*;
import com.example.Ats.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository repository;
    private final ResumeMapper mapper;

    // Create new resume with template
    public ResumeResponseDTO createResume(TemplateRequestDTO dto, String userEmail) {

        Resume resume = new Resume();
        resume.setTemplateName(dto.getTemplateName());
        resume.setUserEmail(userEmail);

        return mapper.toDTO(repository.save(resume));
    }

    // Check authorization and get resume
    private Resume getOwnedResume(Long resumeId, String userEmail) {

        Resume resume = repository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        if (!resume.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access");
        }

        return resume;
    }

    // PERSONAL INFO
    public ResumeResponseDTO updatePersonalInfo(Long resumeId, PersonalInfoDTO dto, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        resume.setName(dto.getName());
        resume.setEmail(dto.getEmail());
        resume.setPhone(dto.getPhone());
        resume.setAddress(dto.getAddress());
        resume.setLinkedin(dto.getLinkedin());
        resume.setGithub(dto.getGithub());
        resume.setCareerObjective(dto.getCareerObjective());

        return mapper.toDTO(repository.save(resume));
    }

    // Education Add
    public ResumeResponseDTO updateEducation(Long resumeId, List<EducationDto> dtoList, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        List<Education> list = dtoList.stream().map(dto -> {
            Education e = new Education();
            e.setLevel(dto.getLevel());
            e.setInstitution(dto.getInstitution());
            // e.setBoardOrUniversity(dto.getBoardOrUniversity());
            e.setStartingYear(dto.getStartingYear());
            e.setEndingYear(dto.getEndingYear());
            e.setPercentageOrCgpa(dto.getPercentageOrCgpa());
            e.setResume(resume);
            return e;
        }).collect(Collectors.toList());

        if (resume.getEducationList() == null)
            resume.setEducationList(new ArrayList<>());

        resume.getEducationList().clear();
        resume.getEducationList().addAll(list);

        return mapper.toDTO(repository.save(resume));
    }

    // SKILLS Add
    public ResumeResponseDTO updateSkills(Long resumeId, List<SkillDto> dtoList, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        List<Skill> list = dtoList.stream().map(dto -> {
            Skill s = new Skill();
            s.setSkillName(dto.getSkillName());
            s.setResume(resume);
            return s;
        }).collect(Collectors.toList());

        if (resume.getSkillList() == null)
            resume.setSkillList(new ArrayList<>());

        resume.getSkillList().clear();
        resume.getSkillList().addAll(list);

        return mapper.toDTO(repository.save(resume));
    }

    //  PROJECTS Add
    public ResumeResponseDTO updateProjects(Long resumeId, List<ProjectDto> dtoList, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        List<Project> list = dtoList.stream().map(dto -> {
            Project p = new Project();
            p.setProjectTitle(dto.getProjectTitle());
            p.setProjectDescription(dto.getProjectDescription());
            p.setGithubLink(dto.getGithubLink());
            p.setResume(resume);
            return p;
        }).collect(Collectors.toList());

        if (resume.getProjects() == null)
            resume.setProjects(new ArrayList<>());

        resume.getProjects().clear();
        resume.getProjects().addAll(list);

        return mapper.toDTO(repository.save(resume));
    }

    // EXPERIENCE Add
    public ResumeResponseDTO updateExperience(Long resumeId, List<WorkExperienceDto> dtoList, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        List<WorkExperience> list = dtoList.stream().map(dto -> {
            WorkExperience w = new WorkExperience();
            w.setCompanyName(dto.getCompanyName());
            w.setPosition(dto.getPosition());
            w.setStartDate(dto.getStartDate());
            w.setEndDate(dto.getEndDate());
            w.setDescription(dto.getDescription());
            w.setResume(resume);
            return w;
        }).collect(Collectors.toList());

        if (resume.getWorkExperience() == null)
            resume.setWorkExperience(new ArrayList<>());

        resume.getWorkExperience().clear();
        resume.getWorkExperience().addAll(list);

        return mapper.toDTO(repository.save(resume));
    }

    // CERTIFICATIONS
    public ResumeResponseDTO updateCertifications(Long resumeId, List<CertificationDto> dtoList, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        List<Certification> list = dtoList.stream().map(dto -> {
            Certification c = new Certification();
            c.setCertificationName(dto.getCertificationName());
            c.setOrganization(dto.getOrganization());
            c.setIssueDate(dto.getIssueDate());
            c.setExpirationDate(dto.getExpirationDate());
            c.setResume(resume);
            return c;
        }).collect(Collectors.toList());

        if (resume.getCertifications() == null)
            resume.setCertifications(new ArrayList<>());

        resume.getCertifications().clear();
        resume.getCertifications().addAll(list);

        return mapper.toDTO(repository.save(resume));
    }

    // ---------------- LANGUAGES ----------------
    public ResumeResponseDTO updateLanguages(Long resumeId, List<LanguageDto> dtoList, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        List<Language> list = dtoList.stream().map(dto -> {
            Language l = new Language();
            l.setLanguageName(dto.getLanguageName());
            l.setProficiencyLevel(dto.getProficiencyLevel());
            l.setResume(resume);
            return l;
        }).collect(Collectors.toList());

        if (resume.getLanguages() == null)
            resume.setLanguages(new ArrayList<>());

        resume.getLanguages().clear();
        resume.getLanguages().addAll(list);

        return mapper.toDTO(repository.save(resume));
    }

    // ACHIEVEMENTS
    public ResumeResponseDTO updateAchievements(Long resumeId, AchievementDTO dto, String userEmail) {

        Resume resume = getOwnedResume(resumeId, userEmail);

        resume.setAchievements(dto.getAchievements());

        return mapper.toDTO(repository.save(resume));
    }

    // Get resume by id
    public ResumeResponseDTO getResumeById(Long resumeId, String userEmail) {
        return mapper.toDTO(getOwnedResume(resumeId, userEmail));
    }

    // Get all resumes of logged-in user
    public List<ResumeResponseDTO> getUserResumes(String userEmail) {
        return repository.findByUserEmail(userEmail)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    //Delete resume
    public void deleteResume(Long resumeId, String userEmail) {
        Resume resume = getOwnedResume(resumeId, userEmail);
        repository.delete(resume);
    }
}