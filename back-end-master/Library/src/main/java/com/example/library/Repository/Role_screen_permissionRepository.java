package com.example.library.Repository;

import com.example.library.Model.Role;
import com.example.library.Model.Role_screen_permission;
import com.example.library.Model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Role_screen_permissionRepository extends JpaRepository<Role_screen_permission , Long> {
    Optional<Role_screen_permission> findByRolesAndScreens(Role role, Screen screen);
    List<Role_screen_permission> findByRoles(Role role);
}
