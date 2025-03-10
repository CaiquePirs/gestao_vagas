package br.com.caiquepires.gestao_vagas.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.caiquepires.gestao_vagas.providers.JWTProviders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProviders jwtProviders;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if (header != null) {
            var subjecToken = this.jwtProviders.validateToken(header);

            if (subjecToken.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            request.setAttribute("company_id", response);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjecToken, null,
                    Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }

}
