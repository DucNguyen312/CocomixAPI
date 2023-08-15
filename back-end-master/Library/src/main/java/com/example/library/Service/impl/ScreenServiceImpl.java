package com.example.library.Service.impl;

import com.example.library.DTO.ScreenDTO;
import com.example.library.DTO.User_OrderDTO;
import com.example.library.DTO.User_Screen_response;
import com.example.library.Model.Role;
import com.example.library.Model.Role_screen_permission;
import com.example.library.Model.Screen;
import com.example.library.Model.Users;
import com.example.library.Repository.Role_screen_permissionRepository;
import com.example.library.Repository.ScreenRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Role_screen_permissionRepository roleScreenPermissionRepository;
    @Override
    public List<Screen> getALLScreen() {
        return screenRepository.findAll();
    }

    @Override
    public Screen getScreenByID(Long id) {
        Optional<Screen> optionalScreen = screenRepository.findById(id);
        if (optionalScreen.isPresent()){
            Screen screen = optionalScreen.get();
            return screen;
        }
        return null;
    }

    @Override
    public String createScreen(ScreenDTO screenDTO) {
        Screen screen = new Screen();
        screen.setName(screenDTO.getName());
        screen.setImages(screenDTO.getImages());
        screen.setDescription(screenDTO.getDescription());
        screenRepository.save(screen);
        return "Create Screen Success";
    }

    @Override
    public String updateScreen(Long id, ScreenDTO screenDTO) {
        Optional<Screen> optionalScreen = screenRepository.findById(id);
        if (optionalScreen.isPresent()){
            Screen screen = optionalScreen.get();
            screen.setName(screenDTO.getName());
            screen.setImages(screenDTO.getImages());
            screen.setDescription(screenDTO.getDescription());
            screenRepository.save(screen);
            return "Update Screen Success";
        }
        return "Not found Screen";
    }

    @Override
    public String deleteScreen(Long id) {
        Optional<Screen> optionalScreen = screenRepository.findById(id);
        if (optionalScreen.isPresent()){
            Screen screen = optionalScreen.get();
            screenRepository.delete(screen);
            return "Delete Screen Success";
        }
        return "Not found Screen";
    }

    @Override
    public String addScreenForUser(Long idUser, Long idScreen) {
        Optional<Users> optionalUsers = userRepository.findById(idUser);
        Optional<Screen> optionalScreen = screenRepository.findById(idScreen);

        if (optionalScreen.isEmpty() || optionalUsers.isEmpty())
            return "Not found Screen or User";

        Screen screen = optionalScreen.get();
        Users users = optionalUsers.get();
        Role role = users.getRoles().iterator().next(); //Nếu nhìu Role thì dùng for

        Optional<Role_screen_permission> optionalRoleScreenPermission = roleScreenPermissionRepository.findByRolesAndScreens(role , screen);
        if (optionalRoleScreenPermission.isEmpty()){
            Role_screen_permission role_screen_permission = new Role_screen_permission();
            role_screen_permission.setScreens(screen);
            role_screen_permission.setRoles(role);
            roleScreenPermissionRepository.save(role_screen_permission);
            return "Add Screen For User Success";
        }
        else
            return "Screen for User already exists";
    }

    @Override
    public String deleteScreenForUser(Long idUser, Long idScreen) {
        Optional<Users> optionalUsers = userRepository.findById(idUser);
        Optional<Screen> optionalScreen = screenRepository.findById(idScreen);
        if (optionalScreen.isEmpty() || optionalUsers.isEmpty())
            return "Not found Screen or User";

        Screen screen = optionalScreen.get();
        Users users = optionalUsers.get();
        Role role = users.getRoles().iterator().next(); //Nếu nhìu Role thì dùng for

        Optional<Role_screen_permission> optionalRoleScreenPermission = roleScreenPermissionRepository.findByRolesAndScreens(role , screen);
        if (optionalScreen.isPresent()){
            Role_screen_permission role_screen_permission = optionalRoleScreenPermission.get();
            roleScreenPermissionRepository.delete(role_screen_permission);
            return "Delete Screen For User Success";
        }
        return "Screen For User not exits";
    }

    @Override
    public List<User_Screen_response> getListScreenForUser(Long idUser) {
        Optional<Users> optionalUsers = userRepository.findById(idUser);
        if (optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            Role role  = users.getRoles().iterator().next();

            List<User_Screen_response> list = new ArrayList<>();
            List<Role_screen_permission> role_screen_permission = roleScreenPermissionRepository.findByRoles(role);
            for (Role_screen_permission r : role_screen_permission){
                Screen screen = r.getScreens();

                User_Screen_response userScreenResponse = new User_Screen_response();
                userScreenResponse.setIdScreen(users.getId());
                userScreenResponse.setIdScreen(screen.getId());
                list.add(userScreenResponse);
            }
            return list;
        }
        return null;
    }
}
