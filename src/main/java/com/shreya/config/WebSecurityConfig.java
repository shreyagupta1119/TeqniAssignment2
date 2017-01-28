package com.shreya.config;

import com.shreya.auth.JwtAuthFilter;
import com.shreya.auth.JwtAuthenticationEntryPoint;
import com.shreya.auth.JwtAuthenticationProvider;
import com.shreya.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by shreya on 11/1/17.
 */
@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthEndPoint;

    MyDBAuthenticationService myDBAauthenticationService=new MyDBAuthenticationService();

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Users in memory.

        auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");

        // For User in database.
        auth.userDetailsService(myDBAauthenticationService);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)  throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers
                ("/", "/welcome", "/login", "/logout","/registration", "/userinfo")
                .permitAll().antMatchers("/admin").hasAuthority("ROLE_ADMIN").and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling().
                authenticationEntryPoint(jwtAuthEndPoint);

        // /userInfo page requires login as USER or ADMIN.
        // If no login, it will redirect to /login page.
       /*http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");*/

        // For ADMIN only.
  //      http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

    }
}
