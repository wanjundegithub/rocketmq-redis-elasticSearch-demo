package com.company.controller;

import com.company.model.Book;
import com.company.service.BookCacheService;
import com.company.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCacheService bookCacheService;

    @PostMapping("/findBooksByName")
    public List<Book> findBookByName(@RequestBody Book book){
        if(book==null || StringUtils.isEmpty(book.getName())){
            return null;
        }
        return bookService.searchBooksByName(book.getName());
    }

    @PostMapping("/findBookByID")
    public Book findBookByID(@RequestBody Book book){
        if(book==null || book.getId()==null){
            return null;
        }
        return bookService.searchBookByID(book.getId());
    }

    @PostMapping("/updateBook")
    public String updateBook(@RequestBody Book book){
        if(book==null || StringUtils.isEmpty(book.getId())){
            return null;
        }
        if(bookService.updateBook(book)){
            return "succeed update book";
        }
        return "fail update book";
    }

    @PostMapping("/cache/findBooks")
    public List<Book> findBookByNameCache(@RequestBody Book book){
        if(book==null || StringUtils.isEmpty(book.getName())){
            return null;
        }
        return bookCacheService.findBookByName(book.getName());
    }

    @PostMapping("/cache/bookID")
    public Book findBookByIdCache(@RequestBody Book book){
        if(book==null || StringUtils.isEmpty(book.getId())){
            return null;
        }
        return bookCacheService.findBookById(book.getId());
    }
}
