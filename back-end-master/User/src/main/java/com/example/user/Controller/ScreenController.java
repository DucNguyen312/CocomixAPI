package com.example.user.Controller;

import com.example.library.DTO.ScreenDTO;
import com.example.library.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screen")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @GetMapping("")
    private ResponseEntity<?> getListScreen(){
        return ResponseEntity.ok(screenService.getALLScreen());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getScreen(@PathVariable(name = "id") Long id){
        if(id == null)
            return ResponseEntity.ok("Not found ID on path");
        return ResponseEntity.ok(screenService.getScreenByID(id));
    }

    @PostMapping("")
    private ResponseEntity<?> createScreen(@RequestBody ScreenDTO screenDTO){
        if (screenDTO == null)
            return ResponseEntity.ok("Not found value");
        return ResponseEntity.ok(screenService.createScreen(screenDTO));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateScreen(@PathVariable(value = "id") Long id, @RequestBody ScreenDTO screenDTO){
        if (id == null)
            return ResponseEntity.ok("Not found id on path");
        if (screenDTO == null)
            return ResponseEntity.ok("Not found value");
        return ResponseEntity.ok(screenService.updateScreen(id,screenDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteScreen(@PathVariable(value = "id") Long id){
        if (id== null)
            return ResponseEntity.ok("Not found id");
        return ResponseEntity.ok(screenService.deleteScreen(id));
    }




}
