package org.example.jobportal.Services;
import org.example.jobportal.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityService {
    private final JwtFilter jwtf;
    public SecurityService(JwtFilter jwtf) {
        this.jwtf = jwtf;
    }

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable()).
                authorizeHttpRequests(auth-> auth.requestMatchers("/addUser","/login").permitAll()
                        .requestMatchers("/addJobs","/getApplications","/shortlist/**","/closeApplications").hasRole("HR")
                        .requestMatchers("/apply/**","/getJobs","/myApplications").hasRole("CANDIDATE").anyRequest()
                        .authenticated()).
                httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtf, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration c){
        return c.getAuthenticationManager();
    }
}
