package com.example.java.payload;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private Long roleId;

    private String roleName;

    private String roleDescription;
}
