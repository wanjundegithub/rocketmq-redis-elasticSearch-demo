package com.company;

import com.company.dao.BookDao;
import com.company.model.Book;
import com.company.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDao bookDao;

    @Test
    public void testSearchBookByName(){
        String name="钢铁是怎样炼成的";
        List<Book> books = bookService.searchBooksByName(name);
        if(CollectionUtils.isEmpty(books)){
            log.error("查询失败");
            return;
        }
        books.forEach(book -> log.info(book.toString()));
        return;
    }

}
