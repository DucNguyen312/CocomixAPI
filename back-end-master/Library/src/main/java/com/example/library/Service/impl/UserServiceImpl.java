package com.example.library.Service.impl;

import com.example.library.DTO.UserDTO;
import com.example.library.Model.Role;
import com.example.library.Model.Users;
import com.example.library.Repository.RoleRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Users saveUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        if(userRepository.existsByUsername(username)){
            return null;
        }
        Users user = new Users();
        user.setFullName(userDTO.getFullName());
        user.setBirthdate(userDTO.getBirthdate());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "Delete user success";
    }

    @Override
    public String login(String username, String password) {
        Users admins = userRepository.findByUsername(username);
        if (admins == null) {
            return "Error: User not found.";
        }
        String encodePassword = admins.getPassword();
        boolean result = passwordEncoder.matches(password, encodePassword);
        if(result)
            return "Login success";
        else
            return "Login Fail";
    }

    @Override
    public Users updateUser(Long id, UserDTO userDTO) {
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            Users admin = user.get();
            admin.setFullName(userDTO.getFullName());
            admin.setBirthdate(userDTO.getBirthdate());
            admin.setEmail(userDTO.getEmail());
            admin.setPhoneNumber(userDTO.getPhoneNumber());
            admin.setUsername(userDTO.getUsername());
            admin.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return userRepository.save(admin);
        }
        return null;
    }

    @Override
    public List<Users> searchUser(String keyword) {
        return userRepository.findByPhoneNumberContainingOrEmailContainingOrUsernameContainingOrFullNameContaining(keyword , keyword , keyword , keyword);
    }

    @Override
    public Users findUserRP(String keyword) {
        return userRepository.findByEmailOrPhoneNumber(keyword , keyword);
    }

    @Override
    public String updatePassword(Long id, String newpassword) {
        Optional<Users> users = userRepository.findById(id);
        if(users.isPresent()) {
            Users admin = users.get();
            admin.setPassword(passwordEncoder.encode(newpassword));
            userRepository.save(admin);
            return "Reset password success";
        }
        return "User not found";
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
