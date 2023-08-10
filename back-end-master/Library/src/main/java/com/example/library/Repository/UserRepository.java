package com.example.library.Repository;

import com.example.library.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByFullName(String name);
    boolean existsByUsername(String username);
    List<Users> findByPhoneNumberContainingOrEmailContainingOrUsernameContainingOrFullNameContaining(String keyword1 , String keyword2 , String keyword3 , String keyword4);
    Users findByEmailOrPhoneNumber(String keyword , String keyword1);
    List<Users> findByRoles_Id(Long id);

    Users findByUsernameOrFullName(String name , String name1);

}
