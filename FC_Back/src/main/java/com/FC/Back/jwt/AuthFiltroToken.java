package com.FC.Back.jwt;

import com.FC.Back.services.MiUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Clase que interceptara las peticiones para asegurarse de validar el token del usuario hacia el servidor
 */
@Component
public class AuthFiltroToken extends OncePerRequestFilter {

    @Autowired
    private MiUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String headerAuth = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(headerAuth != null && headerAuth.startsWith("Bearer ")){
            token = headerAuth.substring(7);
            username = jwtUtil.extraerUsername(token);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validarToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken userPassAuthToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                userPassAuthToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
