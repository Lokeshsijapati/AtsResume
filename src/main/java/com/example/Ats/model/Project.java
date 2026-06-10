package com.example.Ats.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectTitle;

    @Column(length = 2000)
    private String projectDescription;

    private String githubLink;



    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
