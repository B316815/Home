package com.home.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
private JWTResponseFilter jwtResponseFilter;
    public SecurityConfig(JWTResponseFilter jwtResponseFilter) {
        this.jwtResponseFilter = jwtResponseFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.csrf().disable().cors().disable();
//           http.addFilterBefore(jwtResponseFilter, AuthorizationFilter.class);

        http.authorizeHttpRequests().anyRequest().permitAll()

//        http.authorizeHttpRequests(). requestMatchers("/api/b1/auth/login","/api/b1/auth/adduser")
//                .permitAll()
//                .requestMatchers("/api/c1/countries/addCountry").hasRole("ADMIN")
//                .requestMatchers("/api/b1/auth/profile","/api/b1/auth/login","/api/v1/review").hasAnyRole("ADMIN","USER")
//                .anyRequest().authenticated();


                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true);

        return http.build();
    }
}
