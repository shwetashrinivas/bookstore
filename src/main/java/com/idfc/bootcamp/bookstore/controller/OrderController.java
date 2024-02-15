package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.model.OrderBooks;
import com.idfc.bootcamp.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public ResponseEntity<Map<String, String>> buyBooks(@RequestBody List<Long> bookIds) {
        Map<String, String> response = new HashMap<>();
        try {
            orderService.buyBooks(bookIds);
            response.put("message", "Books purchased successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public List<OrderBooks> getAllOrders() {
        return orderService.getAllOrders();
    }
}
