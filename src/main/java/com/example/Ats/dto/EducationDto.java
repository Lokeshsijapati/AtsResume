package com.example.Ats.dto;

import lombok.Data;

@Data
public class EducationDto {

    private String level; // 10th / 12th / Graduation / Masters

    private String institution;

    // private String boardOrUniversity;

    private String startingYear;

    private String endingYear;

    private String percentageOrCgpa;
}