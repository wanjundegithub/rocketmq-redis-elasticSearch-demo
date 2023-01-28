package com.company.dao;

import com.company.model.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    List<Book> findByName(@Param("bookName") String bookName);

    Book findById(@Param("bookID") Long id);

    Integer updateBook(@Param("book") Book book);
}
