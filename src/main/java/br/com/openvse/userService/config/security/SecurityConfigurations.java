package br.com.openvse.userService.config.security;


import br.com.openvse.userService.config.security.filter.JWTAuthenticationFilter;
import br.com.openvse.userService.config.security.util.JWTAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Value("${app.jwt.secret}")
    private String secret;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new JWTAuthenticationProvider(secret));
        auth.authenticationProvider(new AnonymousAuthenticationProvider("key"));
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager());

        http.csrf().disable().
                authenticationManager(authenticationManager())
                .authorizeRequests().antMatchers(HttpMethod.POST,"/student", "/professor","/auth").hasRole("ANON").and()
                .authorizeRequests().antMatchers("/student/**").hasRole("STUDENT").and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/seed").hasRole("ANON").and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic().disable().formLogin().disable();
    }

}
