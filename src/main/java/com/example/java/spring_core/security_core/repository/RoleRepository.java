package com.example.java.spring_core.security_core.repository;

import com.example.java.spring_core.security_core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String roleName);
}
