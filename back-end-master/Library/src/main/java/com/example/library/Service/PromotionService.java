package com.example.library.Service;

import com.example.library.DTO.PromotionDTO;
import com.example.library.Model.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> getListPromotion();
    Promotion getPromotion(Long id);
    String createPromotion(PromotionDTO promotionDTO);
    String updatePromotion(Long id , PromotionDTO promotionDTO);
    String deletePromotion(Long id);
    String applyPromotionForOrder(Long id);
}
