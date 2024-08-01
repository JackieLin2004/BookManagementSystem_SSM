package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.entity.Book;
import org.example.entity.Borrow;

import java.util.List;

public interface BookMapper {

    @Select("""
            select * from book_manage_ssm.borrow left join book_manage_ssm.student on borrow.sid = student.id
             left join book_manage_ssm.book on book_manage_ssm.book.id = borrow.bid
            """)
    List<Borrow> getBorrowList();

    @Select("select * from book_manage_ssm.book")
    List<Book> getBookList();

    @Delete("delete from book_manage_ssm.book where id = #{id}")
    void deleteBook(int id);

    @Insert("insert into book_manage_ssm.book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);
}
