package com.unicam.cs.PiattaformaTuristi;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import com.unicam.cs.PiattaformaTuristi.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class AutenticazioneAPI {

        @Autowired
        private UtenteRepository utenteAutenticatoRepository;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests((requests) -> requests
                                    .requestMatchers("/h2-console/**").permitAll()
                                    .requestMatchers("/login").permitAll()  // Consenti l'accesso alla pagina di login
                                    .requestMatchers(
                                            "/v2/api-docs", // se usi Swagger 2
                                            "/v3/api-docs/**", // se usi OpenAPI 3
                                            "/swagger-resources/**",
                                            "/swagger-ui/**",
                                            "/webjars/**",
                                            "/swagger-ui.html").permitAll()

                                    .requestMatchers("/turista_autenticato/**").permitAll()
                                    .requestMatchers("/contributore/**").permitAll()
                                    .requestMatchers("/contributore_autenticato/**").permitAll()
                                    .requestMatchers("/animatore/**").permitAll()
                                    .requestMatchers("/curatore/**").permitAll()
                                    .requestMatchers("/gestore/**").permitAll()
                            .requestMatchers("/registrazione").permitAll()
                            .anyRequest().authenticated()  // Richiedi autenticazione per qualsiasi altra richiesta
                    )
                    .formLogin((form) -> form
                            .loginPage("/login")
                            .permitAll().successHandler((request, response, authentication) -> response.setStatus(200))
                    )
                    .csrf((csrf) -> csrf.ignoringRequestMatchers("/**"))
                    .headers((headers) -> headers.frameOptions(option -> option.disable()))
                    .logout((logout) -> logout.permitAll());

            return http.build();
        }

        @Bean

        public UserDetailsService userDetailsService() {
            return username -> {
                UtenteAutenticato utenteAutenticato = utenteAutenticatoRepository.ottieniUtenteTramiteUsername(username);
                if (utenteAutenticato != null) {
                    return User
                            .withUsername(utenteAutenticato.getUsername())
                            .password("{noop}"+utenteAutenticato.getPassword())
                            .roles(utenteAutenticato.getRuolo().name())
                            .build();
                } else {
                    throw new UsernameNotFoundException("Utente non trovato.");
                }
            };
        }
}
