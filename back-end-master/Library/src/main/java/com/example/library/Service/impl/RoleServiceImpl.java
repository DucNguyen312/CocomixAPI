package com.example.library.Service.impl;

import com.example.library.DTO.RoleDTO;
import com.example.library.Model.Role;
import com.example.library.Model.Users;
import com.example.library.Repository.RoleRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role addRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, RoleDTO roleDTO) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            Role r = role.get();
            r.setName(roleDTO.getName());
            return roleRepository.save(r);
        }
        return null;
    }

    @Override
    public String deleteRole(Long id) {
        roleRepository.deleteById(id);
        return "Delete Role Success";
    }

    @Override
    public String addUserRole(Long iduser, Long idrole) {
        Optional<Role> role = roleRepository.findById(idrole);
        Optional<Users> users = userRepository.findById(iduser);
        if(role.isPresent() && users.isPresent()){
            Users user = users.get();
            if(user.getRoles() != null)
                return "User already has roles, please update.";
            Collection<Role> roles = user.getRoles();
            roles.add(role.get());
            user.setRoles(roles);
            userRepository.save(user);
            return "Add user to role success";
        }
        return "User or role not exits";
    }

    @Override
    public String deleteUserRole(Long iduser, Long idrole) {
        Optional<Users> user = userRepository.findById(iduser);
        Optional<Role> role = roleRepository.findById(idrole);
        if(user.isPresent() && role.isPresent()){
            Users users = user.get();
            Role roles = role.get();

            Collection<Role> usersRoles = users.getRoles();
            if(usersRoles.contains(roles)){
                usersRoles.remove(roles);
                users.setRoles(usersRoles);
                userRepository.save(users);
                return "User removed from role successfully";
            }
            else
                return "User is not associated with the specified role";
        }
        else
            return "User or role not exits";
    }

    @Override
    public String updateUserRole(Long iduser, Long idrole) {
        Optional<Users> user = userRepository.findById(iduser);
        Optional<Role> role = roleRepository.findById(idrole);
        if (user.isPresent() && role.isPresent()) {
            Users users = user.get();
            Role roles = role.get();

            Collection<Role> roles1 = users.getRoles();
            roles1.clear();
            roles1.add(roles);
            users.setRoles(roles1);
            userRepository.save(users);
            return "User's roles updated successfully.";
        }
        return "User or role does not exist";
    }

}
