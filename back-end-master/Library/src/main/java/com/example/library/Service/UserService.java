package com.example.library.Service;

import com.example.library.DTO.UserDTO;
import com.example.library.Model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Users saveAdmin(UserDTO userDTO);

    List<Users> findAll();

    Optional<Users> findUser(Long id);

    String login(String username , String password);

    String deleteUser(Long id);

    Users updateUser(Long id , UserDTO userDTO);

    List<Users> searchUser(String keyword);

    Users findUserRP(String keyword);

    String updatePassword(Long id , String newpassword);

    String encryptPassword(String password);
}
