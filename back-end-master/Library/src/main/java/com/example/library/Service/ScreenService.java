package com.example.library.Service;

import com.example.library.DTO.ScreenDTO;
import com.example.library.DTO.User_Screen_response;
import com.example.library.Model.Screen;

import java.util.List;
import java.util.Objects;

public interface ScreenService {
    List<Screen> getALLScreen();
    Screen getScreenByID(Long id);
    String createScreen(ScreenDTO screenDTO);
    String updateScreen(Long id, ScreenDTO screenDTO);
    String deleteScreen(Long id);
    String addScreenForUser(Long idUser , Long idScreen);
    String deleteScreenForUser(Long idUser , Long idScreen);

    List<User_Screen_response> getListScreenForUser(Long idUser);
}
