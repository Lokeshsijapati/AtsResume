package com.example.Ats.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "languages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String languageName;
    private String proficiencyLevel;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
