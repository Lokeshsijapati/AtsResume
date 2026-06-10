package com.example.Ats.repository;

import com.example.Ats.model.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {

    List<ResumeAnalysis> findByUserEmail(
            String userEmail
    );
}