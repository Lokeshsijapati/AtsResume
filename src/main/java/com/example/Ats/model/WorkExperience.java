package com.example.Ats.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WorkExperience")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String position;
    private String startDate;
    private String endDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
