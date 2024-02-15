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
    public OrderBooks buyBooks(@RequestBody List<Long> bookIds) {
        return orderService.buyBooks(bookIds);
    }

    @GetMapping
    public List<OrderBooks> getAllOrders() {
        return orderService.getAllOrders();
    }
}
