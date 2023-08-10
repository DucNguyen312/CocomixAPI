package com.example.library.Service;

import com.example.library.DTO.ScreenDTO;
import com.example.library.Model.Screen;

import java.util.List;

public interface ScreenService {
    List<Screen> getALLScreen();
    Screen getScreenByID(Long id);
    String createScreen(ScreenDTO screenDTO);
    String updateScreen(Long id, ScreenDTO screenDTO);
    String deleteScreen(Long id);
}
