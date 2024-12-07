package com.example.java.service;

import com.example.java.model.Role;
import com.example.java.payload.RoleRequest;
import com.example.java.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void createRole(RoleRequest roleRequest) {
         Role role = Role.builder()
                 .roleName(roleRequest.getRoleName())
                 .build();
         roleRepository.save(role);
    }
}
