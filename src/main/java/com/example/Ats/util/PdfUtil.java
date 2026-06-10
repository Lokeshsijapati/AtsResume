package com.example.Ats.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public class PdfUtil {

    public static String extractText(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Empty PDF file");
        }

        try (PDDocument document =
                     PDDocument.load(file.getInputStream())) {

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            if (text == null || text.trim().isEmpty()) {
                throw new RuntimeException("No text found in PDF");
            }

            return text;
        }
    }
}