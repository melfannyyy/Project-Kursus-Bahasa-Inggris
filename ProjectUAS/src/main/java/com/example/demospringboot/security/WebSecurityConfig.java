package com.example.demospringboot.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// klo pake CSRF pake ini
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // source:https://docs.spring.io/spring-security/reference/servlet/configuration/java.html

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            
            // .csrf(csrf -> csrf
            //     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Menggunakan cookie untuk menyimpan token CSRF
            // )

            // source: https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home","/about","/signup/**","/result").permitAll()
                .requestMatchers("/admin/**","/instructor/**").hasRole("ADMIN")
                .requestMatchers("/updatepass","/test").hasRole("USER")
                .anyRequest().authenticated()
            )
            // source: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard",true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL untuk melakukan logout
                .logoutSuccessUrl("/login?logout") // Halaman setelah logout
                .permitAll()
            );
           

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
