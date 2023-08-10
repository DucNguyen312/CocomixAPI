package com.example.library.Service.impl;

import com.example.library.DTO.PromotionDTO;
import com.example.library.Model.Order;
import com.example.library.Model.OrderDetail;
import com.example.library.Model.Promotion;
import com.example.library.Repository.OrderDetailRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.PromotionReponsitory;
import com.example.library.Service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionReponsitory promotionReponsitory;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Promotion> getListPromotion() {
        return promotionReponsitory.findAll();
    }

    @Override
    public Promotion getPromotion(Long id) {
        Optional<Promotion> optionalPromotion = promotionReponsitory.findById(id);
        if (optionalPromotion.isPresent()){
            Promotion promotion = optionalPromotion.get();
            return promotion;
        }
        return null;
    }

    @Override
    public String createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setName(promotionDTO.getName());
        promotion.setDecrease(promotionDTO.getDecrease());
        promotion.setFrom(promotionDTO.getFrom());
        promotion.setTo(promotionDTO.getTo());
        promotion.setApplycondition(promotionDTO.getApplycondition());
        promotionReponsitory.save(promotion);
        return "Create Promotion Success";
    }

    @Override
    public String updatePromotion(Long id, PromotionDTO promotionDTO) {
        Optional<Promotion> optionalPromotion = promotionReponsitory.findById(id);
        if (optionalPromotion.isPresent()){
            Promotion promotion = optionalPromotion.get();
            promotion.setName(promotionDTO.getName());
            promotion.setDecrease(promotionDTO.getDecrease());
            promotion.setFrom(promotionDTO.getFrom());
            promotion.setTo(promotionDTO.getTo());
            promotion.setApplycondition(promotionDTO.getApplycondition());
            promotionReponsitory.save(promotion);
            return "Update Promotion Success";
        }
        return "Not Found Promotion";
    }

    @Override
    public String deletePromotion(Long id) {
        Optional<Promotion> promotion = promotionReponsitory.findById(id);
        if (promotion.isPresent()){
            promotionReponsitory.deleteById(id);
            return "Delete Promotion Success";
        }
        return "Not Found Promotion";
    }

    @Override
    public String applyPromotionForOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            OrderDetail orderDetail = orderDetailRepository.findFirstByOrder(order);

        }
        return null;
    }
}
