package org.example.service;

import java.util.List;
import java.util.Map;

import org.example.entity.Book;
import org.example.entity.Borrow;

public interface BookService {
    List<Borrow> getBorrowList();

    Map<Book, Boolean> getBookList();

    List<Book> getActiveBookList();

    void addBorrow(int sid, int bid);

    void returnBook(int bid);
}
