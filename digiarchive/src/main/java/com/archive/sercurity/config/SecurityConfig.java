package com.archive.sercurity.config;

import com.archive.sercurity.JWTAuthenticationFilter;
import com.archive.sercurity.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
       // auth.inMemoryAuthentication().withUser("admin").password(bCryptPasswordEncoder.encode("abcd")).roles("ADMIN").
     // and().withUser("user").password(bCryptPasswordEncoder.encode("abcd")).roles("USER");

    }


   @Override
   public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.authorizeRequests().antMatchers( "/api/users/create-user", "/login**", "/actuator/**", "/api/documents/get-all-docs",
               "/api/documents/all-docs-list",
               "/api/documents/get-all-docs",                                                                                                                                                       
                "/v2/api-docs",
               "/swagger-resources/**",
               "/swagger-ui.html",
               "/webjars/**" ,
              "/**/**",
              "/swagger.json").permitAll();
       http.authorizeRequests().antMatchers("/contract", "/contract/**").hasAuthority("USER");
       http.authorizeRequests().antMatchers("/account", "/contract/**", "/client/**").hasAnyAuthority("USER", "ADMIN");
       http.authorizeRequests().anyRequest().authenticated();
       http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
       http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
       //http.authorizeRequests().antMatchers("**/**").permitAll();

   }
        /*
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers( "/api/users/create-user", "/login", "/actuator/**")
                .permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/account/**?**", "/contract/**", "/category/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/articles/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/**").hasAnyAuthority("ADMIN", "USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


         */
	
	
}
