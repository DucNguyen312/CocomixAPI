package com.example.library.Service;

import com.example.library.DTO.AccumlatePointsDTO;
import com.example.library.DTO.ExchangePointsDTO;
import com.example.library.Model.AccumlatePoints;

import java.util.List;

public interface PointsService {
    String addPointForUser(Long id, AccumlatePointsDTO accumlatePointsDTO);

    String subtractPointForUser(Long id, AccumlatePointsDTO accumlatePointsDTO);

    List<AccumlatePoints> ListPoitnFotUser(Long id);

    String UpdatePointForProduct(Long id, ExchangePointsDTO exchangePointsDTO);
}