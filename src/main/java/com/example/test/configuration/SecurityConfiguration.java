package com.example.test.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")  // get config value from application.properties
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    // authentication is the process of verifying a principal’s identity against what it claims to be
    // authorization is the process of granting authority to an authenticated user so that this user is allowed to access particular resources
    // access control means controlling access to an application’s resources, making a decision on whether a user is allowed to access a resource

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .usersByUsernameQuery(usersQuery)  // these two need to be implemented for Spring
            .authoritiesByUsernameQuery(rolesQuery)
            .dataSource(dataSource)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    // @PreAuthorize("hasAuthority('USER')")
    // @PostFilter("hasAnyAuthority('ADMIN') or filterObject.owner == authentication.name")
    // @EnableGlobalMethodSecurity(prePostEnabled=true)

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/graphiql").permitAll()
            .antMatchers("/action/*").permitAll()
            .antMatchers("/react/*").permitAll()
            .antMatchers("/api/**").permitAll()

            .antMatchers("/").permitAll()
            .antMatchers("/home").permitAll()
            .antMatchers("/items/**").permitAll()
            .antMatchers("/categories/**").permitAll()
            .antMatchers("/catalogs/**").permitAll()

            .antMatchers("/login").permitAll()
            .antMatchers("/registration").permitAll()

            .antMatchers("/add/item").hasAuthority("USER").anyRequest().authenticated()
            .antMatchers("/delete/item").hasAuthority("USER").anyRequest().authenticated()

            .antMatchers("/user/**").hasAuthority("USER").anyRequest().authenticated()
            .and().formLogin()
            .loginPage("/login").failureUrl("/login?error=true")
            .defaultSuccessUrl("/user/home")
            .usernameParameter("email")
            .passwordParameter("password")
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/").and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
