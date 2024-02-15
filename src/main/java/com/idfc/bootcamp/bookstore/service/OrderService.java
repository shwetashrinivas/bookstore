package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.model.OrderBooks;
import com.idfc.bootcamp.bookstore.model.OrderItem;
import com.idfc.bootcamp.bookstore.repository.BookOrderRepository;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final BookOrderRepository bookOrderRepository;
    private final BookRepository bookRepository;

    @Autowired
    public OrderService(BookOrderRepository bookOrderRepository, BookRepository bookRepository) {
        this.bookOrderRepository = bookOrderRepository;
        this.bookRepository = bookRepository;
    }

    public OrderBooks buyBooks(List<Long> bookIds) {
        if (bookIds == null || bookIds.isEmpty()) {
            throw new IllegalArgumentException("List of book IDs is empty");
        }

        OrderBooks bookOrder = new OrderBooks();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book with id " + bookId + " not found"));
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(1);
            orderItem.setOrder(bookOrder);
            orderItems.add(orderItem);
        }
        bookOrder.setOrderItems(orderItems);
        return bookOrderRepository.save(bookOrder);
    }

    public List<OrderBooks> getAllOrders() {
        return bookOrderRepository.findAll();
    }
}
