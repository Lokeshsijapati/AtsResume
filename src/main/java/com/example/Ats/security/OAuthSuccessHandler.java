package com.example.Ats.security;

import com.example.Ats.model.User;
import com.example.Ats.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        User existing = userRepo.findByEmail(email).orElse(null);

        if (existing == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setProvider("GOOGLE");
            userRepo.save(newUser);
        }

        String token = jwtUtil.generateToken(email);

        response.sendRedirect(
                "http://localhost:3000/oauth-success?token="
                        + token
                        + "&name="
                        + URLEncoder.encode(name, StandardCharsets.UTF_8));
    }
}