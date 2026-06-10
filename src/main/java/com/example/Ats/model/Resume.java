package com.example.Ats.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userEmail; // ownership 
    // Part 1
    private String templateName;

    // Part 2 Presonal Information
    private String name;
    private String email;
    private String phone;
    private String address;
    private String linkedin;
    private String github;
    @Column(length = 1000)
    private String careerObjective;

    // Part 3 Education
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educationList;
    
    // Part 4 Skills
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skillList;

    // Part 5 Projects
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    // Part 6 Work Experience
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)     
    private List<WorkExperience> workExperience;

    // Part 7 Certifications
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certification> certifications;

    // Part 8 Achievements
    @Column(length = 1000)
    private String achievements;

    // Part 9 Languages
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages;
}