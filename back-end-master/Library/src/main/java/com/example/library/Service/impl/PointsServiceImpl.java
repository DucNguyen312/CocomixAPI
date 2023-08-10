package com.example.library.Service.impl;

import com.example.library.DTO.AccumlatePointsDTO;
import com.example.library.DTO.ExchangePointsDTO;
import com.example.library.Model.AccumlatePoints;
import com.example.library.Model.ExchangePoints;
import com.example.library.Model.Product;
import com.example.library.Model.Users;
import com.example.library.Repository.AccumlatePointRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PointsServiceImpl implements PointsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccumlatePointRepository accumlatePointRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addPointForUser(Long id, AccumlatePointsDTO accumlatePointsDTO) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if(optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            AccumlatePoints accumlatePoints = new AccumlatePoints();
            accumlatePoints.setPoints(accumlatePointsDTO.getPoints());
            accumlatePoints.setCreate_time(convertToDate(accumlatePointsDTO.getCreate_time()));
            accumlatePoints.setUpdate_time(convertToDate(accumlatePointsDTO.getUpdate_time()));
            accumlatePoints.setExpiration(accumlatePointsDTO.getExpiration());
            accumlatePoints.setUsers(users);
            accumlatePointRepository.save(accumlatePoints);
            return "Add Accumulate points for users success";
        }
        return "User not exits";
    }

    @Override
    public String subtractPointForUser(Long id, AccumlatePointsDTO accumlatePointsDTO) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if(optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            AccumlatePoints accumlatePoints = new AccumlatePoints();
            accumlatePoints.setPoints(accumlatePointsDTO.getPoints());
            accumlatePoints.setCreate_time(convertToDate(accumlatePointsDTO.getCreate_time()));
            accumlatePoints.setUpdate_time(convertToDate(accumlatePointsDTO.getUpdate_time()));
            accumlatePoints.setExpiration(accumlatePointsDTO.getExpiration());
            accumlatePoints.setUsers(users);
            accumlatePointRepository.save(accumlatePoints);
            return "Subtract points for users success";
        }
        return "User not exits";
    }

    @Override
    public List<AccumlatePoints> ListPoitnFotUser(Long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()){
            return accumlatePointRepository.findAllByUsers_Id(id);
        }
        return null;
    }

    @Override
    public String UpdatePointForProduct(Long id, ExchangePointsDTO exchangePointsDTO) {
        Product product = productRepository.findById(id).orElseThrow(null);
        if (product != null){

            ExchangePoints exchangePoints = new ExchangePoints();

        }
        return null;
    }

    public Timestamp convertToDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateObject = dateFormat.parse(date);
            Timestamp timestamp = new Timestamp(dateObject.getTime());
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
