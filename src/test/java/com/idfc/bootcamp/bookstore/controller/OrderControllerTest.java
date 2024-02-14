package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.model.OrderBooks;
import com.idfc.bootcamp.bookstore.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private OrderService orderService;

    @Test
    @DisplayName("should get all orders")
    void shouldGetAllOrders() throws Exception {
        OrderBooks order1 = new OrderBooks();
        order1.setId(1L);
        OrderBooks order2 = new OrderBooks();
        order2.setId(2L);
        List<OrderBooks> orders = Arrays.asList(order1, order2);

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }
}
