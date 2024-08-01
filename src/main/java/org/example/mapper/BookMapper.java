package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.entity.Book;
import org.example.entity.Borrow;

import java.util.List;

public interface BookMapper {

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "sid", property = "sid"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "time", property = "time"),
            @Result(column = "name", property = "studentName"),
            @Result(column = "title", property = "bookName")
    })
    @Select("""
            select * from book_manage_ssm.borrow left join book_manage_ssm.student on borrow.sid = student.id
             left join book_manage_ssm.book on book_manage_ssm.book.id = book_manage_ssm.borrow.bid
            """)
    List<Borrow> getBorrowList();

    @Insert("insert into book_manage_ssm.borrow(sid, bid, time) values(#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Delete("delete from book_manage_ssm.borrow where id = #{id}")
    void deleteBorrow(@Param("id") int id);

    @Select("select * from book_manage_ssm.book")
    List<Book> getBookList();

    @Delete("delete from book_manage_ssm.book where id = #{id}")
    void deleteBook(int id);

    @Insert("insert into book_manage_ssm.book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);
}
