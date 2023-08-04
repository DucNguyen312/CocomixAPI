package com.example.library.Service;

import com.example.library.DTO.RequestOrder;
import com.example.library.DTO.User_OrderDTO;
import com.example.library.Model.Order;
import com.example.library.Model.OrderDetail;

import java.util.List;
import java.util.Objects;

public interface OrderService {
    List<Order> getAllOrder();
    Order getOrderById(Long id);
    String createOrder(RequestOrder requestOrder);
    String  updateOrder(Long id , RequestOrder requestOrder);
    Order deleteOrder(Long id);
    String confirmOrder(Long id);
    String cancelOrder(Long id);
    List<Order> searchOrder(String name);

    List<User_OrderDTO> ListUserOrder(Long id);
}
