package br.edu.ucsal.petagende.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login.html", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/agenda-tecnico.html").hasRole("TECNICO")
                    .requestMatchers("/agendamento.html").hasRole("ATENDENTE")
                    .requestMatchers("/cadastro.html").hasRole("ATENDENTE") 
                    .anyRequest().authenticated()
                )
            .formLogin(form -> form
                .loginPage("/login.html") 
                .loginProcessingUrl("/login") 
                .successHandler((request, response, authentication) -> {
                    boolean isTecnico = authentication.getAuthorities().stream()
                            .anyMatch(r -> r.getAuthority().equals("ROLE_TECNICO"));
                    
                    if (isTecnico) {
                        response.sendRedirect("/agenda-tecnico.html");
                    } else {
                        response.sendRedirect("/agendamento.html");
                    }
                })
                .failureUrl("/login.html?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html?logout=true")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails atendente = User.builder()
            .username("admin")
            .password("{noop}1234") // {noop} significa que a senha não está criptografada (só para testes)
            .roles("ATENDENTE")
            .build();

        UserDetails tecnico = User.builder()
            .username("tecnico")
            .password("{noop}1234")
            .roles("TECNICO")
            .build();

        return new InMemoryUserDetailsManager(atendente, tecnico);
    }
}