package com.example.admin.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AdminConfig extends WebSecurityConfigurerAdapter {

    public UserDetailsService adminDetailsService(){
        return new AdminConfigService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/admin/**" , "/product/**" , "/category/**" , "/role/**" , "/order/**" , "/market/**" , "/promotion/**" , "/screen/**").permitAll()
                .antMatchers(HttpMethod.DELETE , "/admin/**", "/product/**","/category/**", "/role/**","/order/**", "/market/**", "/promotion/**" , "/screen/**").permitAll()
                .antMatchers(HttpMethod.GET , "/admin/**" , "/product/**","/category/**", "/role/**","/order/**", "/market/**", "/promotion/**" , "/screen/**").permitAll()
                .antMatchers(HttpMethod.PUT , "/admin/**", "/product/**","/category/**", "/role/**","/order/**", "/market/**", "/promotion/**" , "/screen/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");
    }
}
