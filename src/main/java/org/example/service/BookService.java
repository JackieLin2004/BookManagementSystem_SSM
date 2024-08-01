package org.example.service;

import java.util.List;

import org.example.entity.Book;
import org.example.entity.Borrow;

public interface BookService {
    List<Borrow> getBorrowList();

    List<Book> getBookList();

    void addBorrow(int sid, int bid);
}
