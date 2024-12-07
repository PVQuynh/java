package com.example.java.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    // Chuyển đổi mặc định để lấy quyền hạn từ JWT
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    // Thuộc tính xác định tên của trường dùng làm principle
    @Value("${jwt.auth.converter.principle-attribute}")
    private String principleAttribute;

    // ID của resource để trích xuất vai trò
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        // Hợp nhất quyền từ các nguồn mặc định và tùy chỉnh
        Collection<GrantedAuthority> authorities = Stream.concat(
                        // Chuyển đổi quyền mặc định từ JwtGrantedAuthoritiesConverter
                        jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                        // Trích xuất vai trò từ trường realm_access và resource_access
                        extractRolesFromJwt(jwt).stream()
                )
                .collect(toSet());

        // Tạo đối tượng JwtAuthenticationToken đại diện cho người dùng đã xác thực
        return new JwtAuthenticationToken(
                jwt,                  // JWT gốc chứa thông tin xác thực
                authorities,          // Danh sách quyền hạn
                getPrincipleClaimName(jwt) // Giá trị của principle (ví dụ: "sub" hoặc trường khác)
        );
    }

    /**
     * Trích xuất giá trị của principle từ JWT.
     * Nếu không được cấu hình, sử dụng giá trị mặc định là "sub".
     */
    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttribute != null) {
            claimName = principleAttribute;
        }
        return jwt.getClaim(claimName);
    }

    /**
     * Trích xuất vai trò (roles) từ các trường realm_access và resource_access trong JWT.
     */
    private Collection<? extends GrantedAuthority> extractRolesFromJwt(Jwt jwt) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Trích xuất vai trò từ realm_access
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null) {
            List<String> realmRoles = (List<String>) realmAccess.get("roles");
            if (realmRoles != null) {
                realmRoles.forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
                });
            }
        }

        // Trích xuất vai trò từ resource_access
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            for (Map.Entry<String, Object> entry : resourceAccess.entrySet()) {
                String resourceName = entry.getKey();
                Map<String, Object> resourceDetails = (Map<String, Object>) entry.getValue();
                List<String> resourceRoles = (List<String>) resourceDetails.get("roles");
                if (resourceRoles != null) {
                    resourceRoles.forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + resourceName.toUpperCase() + "_" + role.toUpperCase()));
                    });
                }
            }
        }

        return authorities;
    }
}
