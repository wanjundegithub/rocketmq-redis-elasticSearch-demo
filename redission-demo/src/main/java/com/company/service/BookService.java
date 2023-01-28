package com.company.service;

import com.company.dao.BookDao;
import com.company.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> searchBooksByName(String name){
       return bookDao.findByName(name);
    }

    public Book searchBookByID(Long id){
        return bookDao.findById(id);
    }

    public Boolean updateBook(Book book){
        return bookDao.updateBook(book)==1;
    }
}
