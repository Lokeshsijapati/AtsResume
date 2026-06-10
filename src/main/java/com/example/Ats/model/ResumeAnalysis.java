package com.example.Ats.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resume_analysis")
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private Integer resumeMatchPercentage;

    private Integer atsScore;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String matchedSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String missingKeywords;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String recommendedSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String strengths;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String weaknesses;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String suggestions;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String missingSections;

    // @Lob
    // @Column(columnDefinition = "LONGTEXT")
    // private String fullResponse;

    private LocalDateTime createdAt;
}