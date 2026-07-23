package com.careerhub.CareerHub.config;

import com.careerhub.CareerHub.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost:*",
                "https://careerhub-frontend.onrender.com"
        ));

        configuration.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/register",
                                "/login",
                                "/uploads/**"
                        ).permitAll()

                        // Public Job APIs
                        .requestMatchers(
                                HttpMethod.GET,
                                "/jobs",
                                "/jobs/*",
                                "/jobs/search/**"
                        ).permitAll()

                        // Recruiter Job APIs
                        .requestMatchers(HttpMethod.POST, "/jobs")
                        .hasRole("RECRUITER")

                        .requestMatchers(HttpMethod.PUT, "/jobs/**")
                        .hasRole("RECRUITER")

                        .requestMatchers(HttpMethod.DELETE, "/jobs/**")
                        .hasRole("RECRUITER")

                        // Recruiter Dashboard
                        .requestMatchers("/dashboard/recruiter")
                        .hasRole("RECRUITER")

                        // Job Seeker Dashboard
                        .requestMatchers("/dashboard/jobseeker")
                        .hasRole("JOB_SEEKER")

                        // Job Seeker APIs
                        .requestMatchers(
                                "/apply/**",
                                "/applications/my",
                                "/saved-jobs/**"
                        ).hasRole("JOB_SEEKER")

                        // Resume Download
                        .requestMatchers("/applications/*/resume")
                        .hasAnyRole("RECRUITER", "JOB_SEEKER")

                        // Recruiter Application APIs
                        .requestMatchers(
                                "/applications",
                                "/applications/job/**",
                                "/applications/*/status"
                        ).hasRole("RECRUITER")

                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}