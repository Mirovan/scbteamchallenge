package ru.bigint.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
                "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**"
        );
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //declares which Page(URL) will have What access type
        http
                .csrf()
                .disable()
                .httpBasic().disable()

                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/terminal/**").permitAll()
                .antMatchers("/money/**").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")

                // Any other URLs which are not configured in above antMatchers
                // generally declared aunthenticated() in real time
                .anyRequest().authenticated()

                // Login Form Details
                .and()
                .formLogin()
                .loginPage("/")
                .failureUrl("/?error=true")
                .defaultSuccessUrl("/admin", true)
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/user/personal", true)

                // Logout Form Details
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))

                // Exception Details
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/403");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
