package com.example.admin.Controller;

import com.example.library.DTO.RoleDTO;
import com.example.library.Model.Role;
import com.example.library.Service.RoleService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<Role>> getAllRole(){
        return ResponseEntity.ok(roleService.getAllRole());
    }

    @PostMapping("")
    public ResponseEntity<String> saveRole(@RequestBody RoleDTO roleDTO){
        if(roleDTO == null)
            return ResponseEntity.badRequest().body("Not found value");
        Role r = roleService.addRole(roleDTO);
        if(r == null)
            return ResponseEntity.badRequest().body("Add Role Fail");
        return ResponseEntity.ok("Add Role Success");
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<String> updateRole(@PathVariable(value = "roleId") Long roleId , @RequestBody RoleDTO roleDTO){
        if(roleId == null)
            return ResponseEntity.badRequest().body("Not found id");
        if(roleDTO == null)
            return ResponseEntity.badRequest().body("Not found value");
        roleService.updateRole(roleId , roleDTO);
        return ResponseEntity.ok("Update role success");
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable(value = "roleId") Long roleId){
        if(roleId == null)
            return ResponseEntity.badRequest().body("Not found id");
        return ResponseEntity.ok(roleService.deleteRole(roleId));
    }

}
