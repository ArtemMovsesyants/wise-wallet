package ua.dp.amovsesyants.wisewallet.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.dp.amovsesyants.wisewallet.service.JwtService;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final int TOKEN_START_INDEX = 7;


    private final JwtService jwtService;
    private final UserDetailsService jpaUserDetailsService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (hasBearerHeader(authHeader)) {
            String token = extractToken(authHeader);
            String username = jwtService.extractUsername(token);

            if (username != null && !isUserAuthenticated()) {
                UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean hasBearerHeader(final String authHeader) {
        return StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_PREFIX);
    }

    private String extractToken(String authHeader) {
        return Optional.of(authHeader)
                .filter(s -> s.length() > TOKEN_START_INDEX)
                .map(s -> s.substring(TOKEN_START_INDEX))
                .orElseThrow(() -> new BadCredentialsException("Invalid Authorization header: Bearer token is missing or invalid."));
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
