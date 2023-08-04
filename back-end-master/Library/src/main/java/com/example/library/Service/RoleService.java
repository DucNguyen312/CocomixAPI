package com.example.library.Service;

import com.example.library.DTO.RoleDTO;
import com.example.library.Model.Role;
import com.example.library.Model.Users;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    List<Users> getListUserRole(Long idRole);
    Role addRole(RoleDTO roleDTO);
    Role updateRole(Long id , RoleDTO roleDTO);
    String deleteRole(Long id);
    String addUserRole(Long iduser , Long idrole);
    String deleteUserRole(Long iduser , Long idrole);
    String updateUserRole(Long iduser, Long idrole);
}
