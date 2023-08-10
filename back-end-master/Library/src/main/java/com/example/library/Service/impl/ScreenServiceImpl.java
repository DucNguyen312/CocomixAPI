package com.example.library.Service.impl;

import com.example.library.DTO.ScreenDTO;
import com.example.library.Model.Screen;
import com.example.library.Repository.ScreenRepository;
import com.example.library.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
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
}
