package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.model.BookOrder;
import com.idfc.bootcamp.bookstore.model.OrderItem;
import com.idfc.bootcamp.bookstore.repository.BookOrderRepository;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    BookRepository bookRepository;
    BookOrderRepository bookOrderRepository;

    @Autowired
    public BookService(BookOrderRepository bookOrderRepository, BookRepository bookRepository) {
        this.bookOrderRepository = bookOrderRepository;
        this.bookRepository = bookRepository;
    }
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchProductsByName(String searchTerm) {
        return bookRepository.findByTitleContainingIgnoreCase(searchTerm);
    }

    public Optional<Book> getById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public BookOrder buyBooks(List<Long> bookIds) {
        BookOrder bookOrder = new BookOrder();
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

    public List<BookOrder> getAllOrders() {
        return bookOrderRepository.findAll();
    }
}
