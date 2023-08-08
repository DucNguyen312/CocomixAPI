package com.example.library.Service.impl;

import com.example.library.DTO.OrderStatus;
import com.example.library.DTO.RequestOrder;
import com.example.library.DTO.User_OrderDTO;
import com.example.library.Model.Order;
import com.example.library.Model.OrderDetail;
import com.example.library.Model.Product;
import com.example.library.Model.Users;
import com.example.library.Repository.OrderDetailRepository;
import com.example.library.Repository.OrderRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.UserRepository;
import com.example.library.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return  orderRepository.findById(id).orElse(null);
    }

    @Override
    public String updateOrder(Long id, RequestOrder requestOrder) {
        Product product = productRepository.findByName(requestOrder.getTotalproducts());
        if(product == null)
            return "Not found product";
        Order orders = orderRepository.findById(id).orElse(null);
        if(orders != null){
            Order order = orders;
            order.setNgaydat(requestOrder.getNgaydat());
            order.setName(requestOrder.getName());
            order.setAddress(requestOrder.getAddress());
            order.setPhonenumber(requestOrder.getPhonenumber());
            order.setNote(requestOrder.getNote());
            order.setTotalproducts(requestOrder.getTotalproducts());
            order.setProduct(product);
            orderRepository.save(order);

            OrderDetail orderDetail = orderDetailRepository.findFirstByOrder(order);
            orderDetail.setOrder(order);
            orderDetail.setProducts(product);
            orderDetail.setPrice(requestOrder.getPrice());
            orderDetail.setQuantity(requestOrder.getQuantity());
            orderDetail.setTotalprice(requestOrder.getTotalprice());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            orderDetail.setCreate_time(timestamp);
            orderDetailRepository.save(orderDetail);
        }
        return "Update Order Success";
    }

    @Override
    public Order deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        orderRepository.delete(order);
        return order;
    }

    @Override
    public String createOrder(RequestOrder requestOrder) {
        Product product = productRepository.findByName(requestOrder.getTotalproducts());
        Users users = userRepository.findByUsernameOrFullName(requestOrder.getName() , requestOrder.getName());

        if(product == null) return "Not found product";
        if (users == null) return "Not found user";

        Order order = new Order();
        order.setNgaydat(requestOrder.getNgaydat());
        order.setName(requestOrder.getName());
        order.setAddress(requestOrder.getAddress());
        order.setPhonenumber(requestOrder.getPhonenumber());
        order.setNote(requestOrder.getNote());
        order.setTotalproducts(requestOrder.getTotalproducts());
        order.setProduct(product);
        orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProducts(product);
        orderDetail.setUsers(users);
        orderDetail.setPrice(requestOrder.getPrice());
        orderDetail.setQuantity(requestOrder.getQuantity());
        orderDetail.setTotalprice(requestOrder.getTotalprice());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orderDetail.setCreate_time(timestamp);

        orderDetailRepository.save(orderDetail);
        return "Add order success";
    }

    @Override
    public String confirmOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null)
            return "Not found id";
        Order orders = order;
        orders.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(orders);
        return "Order Confirmation";
    }

    @Override
    public String cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null)
            return "Not found id";
        Order orders = order;
        orders.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(orders);
        return "Cancel the order";
    }

    @Override
    public List<Order> searchOrder(String name) {
        return orderRepository.findByNameContaining(name);
    }

    @Override
    public List<User_OrderDTO> ListUserOrder(Long id) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByUsers_Id(id);
        if(orderDetails == null)
            return null;

        List<User_OrderDTO> objectList = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            Users users = orderDetail.getUsers();
            Order order = orderDetail.getOrder();
            User_OrderDTO orderDTO = new User_OrderDTO();
            orderDTO.setIduser(users.getId());
            orderDTO.setNgaydat(order.getNgaydat());
            orderDTO.setName(order.getName());
            orderDTO.setAddress(order.getAddress());
            orderDTO.setIdorder(order.getId());

            objectList.add(orderDTO);
        }
        return objectList;
    }
}
