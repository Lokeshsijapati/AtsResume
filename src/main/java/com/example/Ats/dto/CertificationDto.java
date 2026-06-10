package com.example.Ats.dto;

import lombok.Data;

@Data
public class CertificationDto {

    private String certificationName;

    private String organization;

    private String issueDate;

    private String expirationDate;
}