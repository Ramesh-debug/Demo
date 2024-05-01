package com.udemy.securitycourse1.configurations;

import com.udemy.securitycourse1.DetailsService.JwtTokensService;
import com.udemy.securitycourse1.DetailsService.MyUserDetails;
import com.udemy.securitycourse1.filter.NewJwtValidation;
import com.udemy.securitycourse1.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtTokensService jwtService;
    @Bean
    public NewJwtValidation newJwtValidation() {
        return new NewJwtValidation(jwtService);
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.addFilterAfter(new JwtTokenGenerationFilter(), BasicAuthenticationFilter.class);
//        http.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(newJwtValidation(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/contact","/notices").hasRole("ADMIIN")
                        .requestMatchers("/authenticate","/register").permitAll()
                        .requestMatchers("/loans","/account","/balance","/cards").hasRole("USER"));


        http.csrf(c-> c.disable());


//            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();


        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetails()
//    {
//        UserDetails admin=User.withDefaultPasswordEncoder()
//                .username("Ramesh")
//                .password("1234")
//                .roles("ADMIN").build();
//        UserDetails user=User.withDefaultPasswordEncoder()
//                .username("User")
//                .password("12345")
//                .roles("USER").build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }

    @Autowired
    MyUserDetails myUserDetails;
    @Bean
    public UserDetailsService userDetailsService()
    {
        return myUserDetails;
    }

    @Autowired
    public DetailsRepository detailsRepo;

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetails);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
