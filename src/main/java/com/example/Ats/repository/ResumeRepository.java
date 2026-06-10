package com.example.Ats.repository;

import com.example.Ats.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByUserEmail(String userEmail);
}