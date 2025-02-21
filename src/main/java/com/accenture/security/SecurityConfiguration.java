package com.accenture.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        //CLIENTS
                        .requestMatchers("/clients/**").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/clients/**").hasRole("ADMIN")
                        //.requestMatchers(HttpMethod.POST, "/clients/**").permitAll()
                        //.requestMatchers(HttpMethod.PUT, "/clients/**").hasRole("CLIENT")
                        //.requestMatchers(HttpMethod.PATCH, "/clients/**").hasRole("CLIENT")
                        //.requestMatchers(HttpMethod.DELETE, "/clients/**").hasAnyRole("CLIENT", "ADMIN")
                        //ADMINS
                        .requestMatchers("/admins/**").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/admins/**").hasRole("ADMIN")
                        //.requestMatchers(HttpMethod.POST, "/admins/**").hasRole("ADMIN")
                        //.requestMatchers(HttpMethod.PUT, "/admins/**").hasRole("ADMIN")
                        //.requestMatchers(HttpMethod.PATCH, "/admins/**").hasRole("ADMIN")
                        //.requestMatchers(HttpMethod.DELETE, "/admins/**").hasRole("ADMIN")
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                );
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //@Bean
    //UserDetailsManager clientDetailsManager(DataSource dataSource) {
    //    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    //    jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email, password, 1 FROM client WHERE email = ?");
    //    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, role FROM client WHERE email = ?");
    //    return jdbcUserDetailsManager;
    //}

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("""
                SELECT email, password, 1
                FROM administrateur
                WHERE email = ?
                UNION
                SELECT email, password, 1
                FROM client
                WHERE email = ?
                """);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("""
                SELECT email, role
                FROM administrateur
                WHERE email = ?
                UNION
                SELECT email, role
                FROM client
                WHERE email = ?""");
        return jdbcUserDetailsManager;
    }
}
