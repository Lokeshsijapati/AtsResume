package com.example.Ats.util;

public class ResumeAiPromptBuilder {

    private ResumeAiPromptBuilder() {
    }

    public static String build(String resumeContent) {

        return """
                You are an expert ATS Resume Writer and Reviewer.

                Analyze the resume and generate READY-TO-USE ATS optimized content.

                IMPORTANT RULES:

                1. Do NOT give generic advice.
                2. Do NOT explain improvements.
                3. Generate professional content that can be directly copied into a resume.
                4. Rewrite weak content completely.
                5. If a section is missing, generate ATS-friendly sample content.
                6. All content must be recruiter-friendly and ATS optimized.
                7. Keep content concise, professional, and impactful.
                8. Return ONLY valid JSON.
                9. No markdown.
                10. No explanations outside JSON.
                11. Return ALL fields every time.
                12. If a field has no suggestions, return an empty array [].
                13. Never return null values.
                14. Every suggestion should be copy-paste ready.
                15. Missing sections must contain both section name and sample content.

                Expected JSON Format:

                {
                  "careerObjectiveSuggestions": [
                    "Results-driven Java Full Stack Developer seeking to leverage expertise in Spring Boot, React, and REST APIs to build scalable enterprise applications."
                  ],

                  "projectDescriptionSuggestions": [
                    "Developed a full-stack employee management system using Spring Boot, React, and MySQL, enabling efficient employee record management and reducing manual effort by 40%%."
                  ],

                  "experienceDescriptionSuggestions": [
                    "Designed and implemented scalable backend APIs using Spring Boot and Hibernate, improving application performance and supporting seamless frontend integration."
                  ],

                  "grammarImprovements": [
                    "Motivated Java Developer with strong problem-solving skills and hands-on experience in developing web applications."
                  ],

                  "professionalWordingSuggestions": [
                    "Collaborated with cross-functional teams to deliver high-quality software solutions within project deadlines."
                  ],

                  "betterResumeSentences": [
                    "Built and deployed RESTful web services using Spring Boot, improving system efficiency and maintainability."
                  ],

                  "missingImportantSections": [
                    {
                      "sectionName": "Projects",
                      "sampleContent": "Developed an E-Commerce Web Application using Spring Boot, React, and MySQL with features including authentication, product management, and order tracking."
                    },
                    {
                      "sectionName": "Certifications",
                      "sampleContent": "Oracle Certified Professional Java SE Developer."
                    }
                  ]
                }

                Analyze the following resume and generate copy-paste ready ATS content.

                Resume:

                %s
                """.formatted(resumeContent);
    }
}