package com.example.admin.Controller;

import com.example.library.DTO.UserDTO;
import com.example.library.Model.Users;
import com.example.library.Service.CacheService;
import com.example.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;
    @GetMapping("")
    public ResponseEntity<List<Users>> getAll(@RequestParam(value = "query" , required = false) String keyword){
        if(keyword != null)
            return ResponseEntity.ok(userService.searchUser(keyword));
        else
            return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable Long id) {
       return ResponseEntity.ok(userService.findUser(id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody UserDTO admin){
        if(admin == null)
            return ResponseEntity.badRequest().body("Register Fail");
        userService.saveAdmin(admin);
        return ResponseEntity.ok("Register success");
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestParam(value = "username") String username , @RequestParam(value = "password") String password){
        return ResponseEntity.ok(userService.login(username , password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable Long id){
        if(id == null)
            return ResponseEntity.badRequest().body("ID not found");
        userService.deleteUser(id);
        return  ResponseEntity.ok("Delete User Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> UpdateUser(@PathVariable Long id , @RequestBody UserDTO userDTO){
        if(id == null) return null;
        if(userDTO != null){
            return ResponseEntity.ok(userService.updateUser(id , userDTO));
        }
        return null;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> reqResetPassword(@RequestParam(value = "keyword") String keyword){
        if(keyword == null)
            return ResponseEntity.badRequest().body("Not found value");
        Users users = userService.findUserRP(keyword);
        if(users == null)
            return ResponseEntity.badRequest().body("Not found user");

        return ResponseEntity.ok("http://localhost:8005/admin/forgot-password/"+cacheService.generateAndCacheToken(keyword));
    }

    @PostMapping("/forgot-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestParam(value = "newPassword") String newPassword){
        String s = cacheService.getEmailFromToken(token);
        if(s == null)
            return ResponseEntity.badRequest().body("Not found User");
        Users admin = userService.findUserRP(s);
        userService.updatePassword(admin.getId() , newPassword);
        cacheService.removeToken(token);
        return ResponseEntity.ok("Reset password success");
    }

    @PostMapping("/encrypt-password")
    public ResponseEntity<String> encryptPassword(@RequestParam(value = "password") String password){
        if(password==null)
            return ResponseEntity.badRequest().body("not found password");
        return ResponseEntity.ok(userService.encryptPassword(password));
    }


}
