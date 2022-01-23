package com.FC.Back.security;

import com.FC.Back.jwt.AuthFiltroToken;
import com.FC.Back.services.MiUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MiUserDetailsService userDetailsService;

    @Autowired
    private AuthFiltroToken authFiltroToken;

    @Bean
    public BCryptPasswordEncoder passEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    /**
     * Indicamos que queremos una autenticacion personalizada en este caso definimos el comportamiento
     * del <b>serDetailsService</b> en nuestra clase {@link MiUserDetailsService}, esto permite personalizar
     * la autenticacion.
     * Tambien indicamos que debe cifrar la contraseña que se cree o que se analice.
     * @param auth usado para indicar la autenticacion por medio de la BD.
     * @throws Exception si existe un problema con la autenticacion.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passEncoder());
    }

    /**
     * Configuramos las rutas y/o recursos que queremos proteger y cuales establacer de modo publico, ademas de
     * configurar nuestros propios filtros, login o logout.
     * @param http URL usada para comparar el acceso
     * @throws Exception Si no tiene acceso a los recursos
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login")
                .permitAll().
                 antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authFiltroToken, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS", "DELETE", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
