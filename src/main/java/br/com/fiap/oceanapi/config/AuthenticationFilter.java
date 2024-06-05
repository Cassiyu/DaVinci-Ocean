package br.com.fiap.oceanapi.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.oceanapi.model.Usuario;
import br.com.fiap.oceanapi.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Component
@Order(1)
@Log4j2
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getToken(request);
        log.info("Token obtido: {}", token);

        if (token != null) {
            Usuario user = tokenService.validateToken(token);
            if (user != null) {
            
                log.info("Autenticação bem-sucedida para o usuário: {}", user.getUsername());
            } else {
                log.warn("Token inválido ou usuário não encontrado");
            }
        } else {
            log.warn("Nenhum token presente na solicitação");
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        log.info("Header = {}", header);

        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }

        return null;
    }
}