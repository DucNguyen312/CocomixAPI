package com.example.library.Repository;

import com.example.library.Model.Order;
import com.example.library.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail findFirstByOrder(Order order);
    List<OrderDetail> findByUsers_Id(Long id);

}
