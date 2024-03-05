package com.workintech.eCommerceBackend.config;

import com.workintech.eCommerceBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//    @Autowired
//    private UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authManager(UserDetailsService userDetailsService){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return new ProviderManager(provider);
//    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/v1/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/v1/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/v1/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/v1/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/v1/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/v1/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/v1/users/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/users/**").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/v1/users/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/v1/users/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/v1/address/**").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/v1/address/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/v1/address/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/address/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/v1/roles/**").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/v1/roles/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/v1/roles/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/roles/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/v1/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/v1/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/v1/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/v1/cards/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/v1/cards/**").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/v1/cards/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/v1/cards/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}