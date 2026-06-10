package com.example.Ats.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "education")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level; 

    private String institution;

    // private String boardOrUniversity;

    private String startingYear;
    private String endingYear;

    private String percentageOrCgpa;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}