package com.example.user.Controller;

import com.example.library.DTO.RequestOrder;
import com.example.library.Model.Order;
import com.example.library.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<Order>> getALLOrder(@RequestParam(value = "query" , required = false) String keyword){
        if(keyword != null)
            return ResponseEntity.ok(orderService.searchOrder(keyword));
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderByID(@PathVariable(value = "id") Long id){
        if(id == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> addOrder(@RequestBody RequestOrder requestOrder){
        if(requestOrder == null)
            return ResponseEntity.badRequest().body("Not found Value");
        return ResponseEntity.ok(orderService.createOrder(requestOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable(value = "id") Long id , @RequestBody RequestOrder requestOrder){
        if(id == null)
            return ResponseEntity.ok("Not found Id");
        if(requestOrder == null)
            return ResponseEntity.badRequest().body("Not found Value");
        return ResponseEntity.ok(orderService.updateOrder(id ,requestOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeOrder(@PathVariable(value = "id") Long id){
        if(id == null)
            return ResponseEntity.badRequest().body("Not found id");
        Order order = orderService.deleteOrder(id);
        if(order == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Delete Order Success");
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable(value = "id")Long id){
        if(id == null)
            return ResponseEntity.badRequest().body("Not found Value");
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<String> confirmOrder(@PathVariable(value = "id")Long id){
        if(id == null)
            return ResponseEntity.badRequest().body("Not found Value");
        return ResponseEntity.ok(orderService.confirmOrder(id));
    }


}
