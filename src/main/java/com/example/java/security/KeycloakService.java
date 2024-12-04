package com.example.java.security;

import com.example.java.config.KeycloakConfig;
import com.example.java.payload.ChangePasswordRequest;
import com.example.java.payload.RegisterRequest;
import com.example.java.payload.RoleRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.common.util.Time;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakService {
    private final KeycloakConfig keycloakConfig;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    // Tạo token mới
    public String generateToken(String username, String password) {
        Keycloak keycloak = Keycloak.getInstance(
                serverUrl,
                realm,
                username,
                password,
                clientId,
                clientSecret);
        String accessToken = keycloak.tokenManager().getAccessTokenString();
        return accessToken;
    }

    // Tạo refresh token mới
    public String generateRefreshToken(UserDetails userDetails) {
        String refreshToken = UUID.randomUUID().toString();
        return refreshToken;
    }

    // Check token còn hiệu lực không


    // =================================================================================================

    public List<UserRepresentation> searchUserByEmail(String email) {
        Keycloak keycloak = keycloakConfig.getKeycloakInstance();
        try {
            List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().list();
            UsersResource usersResource = keycloak.realm(realm).users();
            return usersResource.search(email);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (keycloak != null) {
                keycloak.close();
            }
        }
        return null;
    }

    public void createUser(RegisterRequest registerReq) {
        String email = registerReq.getEmail();

        if (!ObjectUtils.isEmpty(searchUserByEmail(email))) {
            throw new RuntimeException("Email của bạn đã được sử dụng");
        }

        Keycloak keycloak = keycloakConfig.getKeycloakInstance();
        UsersResource userResource = keycloak.realm(realm).users();

        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setTemporary(false);
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(registerReq.getPassword());
        passwordCredential.setCreatedDate((long) Time.currentTime());

        UserRepresentation user = new UserRepresentation();
        user.setEmail(email);
        user.setUsername(email);
        user.setCredentials(Collections.singletonList(passwordCredential));
        user.setEnabled(true);
        Response res = userResource.create(user);
        int statusCode = res.getStatus();

        if (statusCode != 201) {
            throw new BadRequestException("Không thể đăng kí được tài khoản. Vui lòng thử lại!");
        }

        if (statusCode == 201) {
            // Gán role cho người dùng
            String userId = res.getLocation().getPath().replaceAll(".*/([^/]+)", "$1");
            UserRepresentation createdUser = userResource.get(userId).toRepresentation();

            RoleMappingResource roleMappingResource = userResource.get(userId).roles();
            RoleRepresentation roleToAdd = keycloak.realm(realm).roles().get(registerReq.getRole()).toRepresentation();
            roleMappingResource.realmLevel().add(Collections.singletonList(roleToAdd));

        }

    }

    public void createRole(RoleRequest roleRequest) {
        Keycloak keycloak = keycloakConfig.getKeycloakInstance();
        RealmResource realmResource = keycloak.realm(realm);

        // Tạo một đối tượng RoleRepresentation và đặt tên và mô tả cho role
        RoleRepresentation role = new RoleRepresentation();
        role.setName(roleRequest.getRoleName());
        role.setDescription(roleRequest.getRoleDescription());

        realmResource.roles().create(role);
    }

    public void deleteUserByEmail(String email) {

        Keycloak keycloak = keycloakConfig.getKeycloakInstance();
        UsersResource userResource = keycloak.realm(realm).users();
        UserRepresentation user = new UserRepresentation();
        userResource.get(email).remove();
    }


    public void changePassword(ChangePasswordRequest changePasswordReq) {
        Keycloak keycloak = keycloakConfig.getKeycloakInstance();
        String email = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!ObjectUtils.isEmpty(authentication)) {
            email = (String) authentication.getPrincipal();
        }
        UsersResource userResource = keycloak.realm(realm).users();


        UserRepresentation existingUserRepresentation = userResource.search(email).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));


        UserResource existingUser = userResource.get(existingUserRepresentation.getId());


        CredentialRepresentation newPasswordCredential = new CredentialRepresentation();
        newPasswordCredential.setTemporary(false);
        newPasswordCredential.setType(CredentialRepresentation.PASSWORD);
        newPasswordCredential.setValue(changePasswordReq.getNewPassword());


        existingUser.resetPassword(newPasswordCredential);
    }

    public void randomlyGeneratePassword(String email, String password) {
        Keycloak keycloak = keycloakConfig.getKeycloakInstance();
        UsersResource userResource = keycloak.realm(realm).users();

        UserRepresentation existingUserRepresentation = userResource.search(email).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        UserResource existingUser = userResource.get(existingUserRepresentation.getId());

        CredentialRepresentation newPasswordCredential = new CredentialRepresentation();
        newPasswordCredential.setTemporary(false);
        newPasswordCredential.setType(CredentialRepresentation.PASSWORD);
        newPasswordCredential.setValue(password);

        existingUser.resetPassword(newPasswordCredential);
    }
}

