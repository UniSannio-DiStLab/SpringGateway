package it.unisannio;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER")
//.anyRequest().authenticated()
                .and()
//.formLogin()
//.loginProcessingUrl("/login")
//.and()
                .logout()
                .permitAll();

    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin1pass").roles("USER", "ADMIN").and()
                .withUser("user1").password("{noop}user1pass").roles("USER").and()
                .withUser("user2").password("{noop}user2pass").roles("USER").and()
                .withUser("user3").password("{noop}user3pass").roles("USER");
    }

}