package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.model.OrderBooks;
import com.idfc.bootcamp.bookstore.repository.BookOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private BookOrderRepository bookOrderRepository;

    @BeforeEach
    void setUp() {
        OrderBooks order1 = new OrderBooks();
        order1.setId(1L);
        OrderBooks order2 = new OrderBooks();
        order2.setId(2L);
        List<OrderBooks> orders = Arrays.asList(order1, order2);

        when(bookOrderRepository.findAll()).thenReturn(orders);
    }

    @Test
    void testGetAllOrders() {
        List<OrderBooks> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
        assertEquals(1L, orders.get(0).getId());
        assertEquals(2L, orders.get(1).getId());
    }
}
