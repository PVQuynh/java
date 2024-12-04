package com.example.java.security;

import com.example.java.payload.KeycloakInfo;
import com.example.java.repository.TokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Những api không cần check
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response); // lọc và thoát
            return;
        }

        // Kiểm tra có sử dụng Authorization không?
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // lọc và thoát
            return;
        }

        // Lấy jwt từ request
        final String jwt = authHeader.substring(7);

        //  ==================================================================================================================================

//        // Lấy username từ chuỗi jwt
//        final String userEmail= jwtService.extractUsername(jwt);
//        // Lấy UserDetails từ username
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

        // Lấy thông tin người dùng từ JWT
        KeycloakInfo userInfo = null;
        String jwtPayload = jwt.split("\\.")[1]; // Tách phần payload từ JWT
        byte[] decodedBytes = Base64.getDecoder().decode(jwtPayload); // Giải mã phần payload từ Base64
        String jsonPayload = new String(decodedBytes, StandardCharsets.UTF_8); //// Chuyển đổi byte array thành chuỗi UTF-8
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userInfo = objectMapper.readValue(jsonPayload, KeycloakInfo.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        // Lấy email người dùng từ đối tượng KeycloakInfo
        final String userEmail = userInfo != null ? userInfo.getEmail() : null;

        // ==================================================================================================================================

        // Thực hiện kiểm tra token
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            // Nếu token hợp lệ thì thêm vào thông tin cho Seturity Context
//            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//                // jwt
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }

            // Nếu token hợp lệ thì thêm vào thông tin cho Seturity Context
            if (true) {
                // keycloak
                KeycloakInfo.RealmAccess roleRealmAccess = userInfo.getRealmAccess();
                String role = roleRealmAccess.getRoles().get(3);
                Set<GrantedAuthority> grantedAuthority = Collections.singleton(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return role;
                    }
                });
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userEmail,
                        null,
                        grantedAuthority
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response); // lọc và thoát
        return;
    }
}
