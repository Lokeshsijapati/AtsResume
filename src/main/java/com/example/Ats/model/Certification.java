package com.example.Ats.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certifications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certificationName;

    private String organization;

    private String issueDate;

    private String expirationDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}