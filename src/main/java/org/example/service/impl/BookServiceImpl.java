package org.example.service.impl;

import jakarta.annotation.Resource;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.mapper.BookMapper;
import org.example.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;

    @Override
    public List<Borrow> getBorrowList() {
        return mapper.getBorrowList();
    }

    @Override
    public List<Book> getBookList() {
        return mapper.getBookList();
    }
}
