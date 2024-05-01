package com.udemy.securitycourse1.filter;
import com.udemy.securitycourse1.DetailsService.JwtTokensService;
import com.udemy.securitycourse1.DetailsService.MyUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class NewJwtValidation extends OncePerRequestFilter {

    private final JwtTokensService jwtService;

    @Autowired
    MyUserDetails userDetailsService;

    public NewJwtValidation(JwtTokensService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);
        if (token != null) {
            UserDetails userDetails = getUserDetailsFromToken(token);
            if (userDetails != null && jwtService.validateToken(token, userDetails)) {
                // Token is valid, set up authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // Extract token from Authorization header or any other location
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private UserDetails getUserDetailsFromToken(String token) {
        String username = jwtService.extractUsername(token);
        // Retrieve UserDetails from your UserDetailsService (e.g., MyUserDetails)
        return userDetailsService.loadUserByUsername(username);
    }
}
