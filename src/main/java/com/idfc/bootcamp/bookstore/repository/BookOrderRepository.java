package com.idfc.bootcamp.bookstore.repository;

import com.idfc.bootcamp.bookstore.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}
