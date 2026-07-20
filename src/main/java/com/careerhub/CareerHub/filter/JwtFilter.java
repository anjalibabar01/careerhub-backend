package com.careerhub.CareerHub.filter;

import com.careerhub.CareerHub.jwt.JwtUtil;
import com.careerhub.CareerHub.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtFilter(
            JwtUtil jwtUtil,
            CustomUserDetailsService customUserDetailsService) {

        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        System.out.println("Request path = " + path);

        if (path.equals("/login")
                || path.equals("/register")) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader != null
                && authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);

            try {

                String email =
                        jwtUtil.extractUsername(token);

                UserDetails userDetails =
                        customUserDetailsService
                                .loadUserByUsername(email);

                if (jwtUtil.validateToken(
                        token,
                        userDetails)) {

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    auth.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(auth);
                }

            } catch (Exception e) {

                System.out.println(
                        "JWT Error: " + e.getMessage()
                );
            }
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}