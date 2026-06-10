package com.example.Ats.controller;

import com.example.Ats.dto.*;
import com.example.Ats.service.ResumeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

        private final ResumeService resumeService;

        // Create new resume
        @PostMapping("/create")
        public ResponseEntity<ResumeResponseDTO> createResume(
                        @RequestBody TemplateRequestDTO dto,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.createResume(dto, userEmail);

                return ResponseEntity.status(201).body(response);
        }

        // Update personal details
        @PutMapping("/{resumeId}/personal-info")
        public ResponseEntity<ResumeResponseDTO> updatePersonalInfo(
                        @PathVariable Long resumeId,
                        @RequestBody PersonalInfoDTO dto,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updatePersonalInfo(
                                resumeId,
                                dto,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Add or update education details
        @PutMapping("/{resumeId}/education")
        public ResponseEntity<ResumeResponseDTO> updateEducation(
                        @PathVariable Long resumeId,
                        @RequestBody List<EducationDto> dtoList,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updateEducation(
                                resumeId,
                                dtoList,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Add skills
        @PutMapping("/{resumeId}/skills")
        public ResponseEntity<ResumeResponseDTO> updateSkills(
                        @PathVariable Long resumeId,
                        @RequestBody List<SkillDto> dtoList,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updateSkills(
                                resumeId,
                                dtoList,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Add projects
        @PutMapping("/{resumeId}/projects")
        public ResponseEntity<ResumeResponseDTO> updateProjects(
                        @PathVariable Long resumeId,
                        @RequestBody List<ProjectDto> dtoList,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updateProjects(
                                resumeId,
                                dtoList,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Add work experience
        @PutMapping("/{resumeId}/experience")
        public ResponseEntity<ResumeResponseDTO> updateExperience(
                        @PathVariable Long resumeId,
                        @RequestBody List<WorkExperienceDto> dtoList,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updateExperience(
                                resumeId,
                                dtoList,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Add certifications
        @PutMapping("/{resumeId}/certifications")
        public ResponseEntity<ResumeResponseDTO> updateCertifications(
                        @PathVariable Long resumeId,
                        @RequestBody List<CertificationDto> dtoList,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updateCertifications(
                                resumeId,
                                dtoList,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Update achievements
        @PutMapping("/{resumeId}/achievements")
        public ResponseEntity<ResumeResponseDTO> updateAchievements(
                        @PathVariable Long resumeId,
                        @RequestBody AchievementDTO dto,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updateAchievements(
                                resumeId,
                                dto,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Add languages
        @PutMapping("/{resumeId}/languages")
        public ResponseEntity<ResumeResponseDTO> updateLanguages(
                        @PathVariable Long resumeId,
                        @RequestBody List<LanguageDto> dtoList,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.updateLanguages(
                                resumeId,
                                dtoList,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Get resume by id
        @GetMapping("/{resumeId}")
        public ResponseEntity<ResumeResponseDTO> getResume(
                        @PathVariable Long resumeId,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                ResumeResponseDTO response = resumeService.getResumeById(
                                resumeId,
                                userEmail);

                return ResponseEntity.ok(response);
        }

        // Get all resumes of logged-in user
        @GetMapping("/user")
        public ResponseEntity<List<ResumeResponseDTO>> getUserResumes(
                        Authentication authentication) {

                String userEmail = authentication.getName();

                List<ResumeResponseDTO> list = resumeService.getUserResumes(userEmail);

                return ResponseEntity.ok(list);
        }

        // Delete resume
        @DeleteMapping("/{resumeId}")
        public ResponseEntity<String> deleteResume(
                        @PathVariable Long resumeId,
                        Authentication authentication) {

                String userEmail = authentication.getName();

                resumeService.deleteResume(
                                resumeId,
                                userEmail);

                return ResponseEntity.ok(
                                "Resume deleted successfully");
        }



        
}