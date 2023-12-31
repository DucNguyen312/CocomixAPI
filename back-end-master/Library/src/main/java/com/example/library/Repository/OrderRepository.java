package com.example.library.Repository;

import com.example.library.Model.Order;
import com.example.library.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByNameContaining(String name);



}
