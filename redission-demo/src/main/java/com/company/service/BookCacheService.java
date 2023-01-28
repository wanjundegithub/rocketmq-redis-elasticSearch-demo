package com.company.service;

import com.company.dao.BookDao;
import com.company.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BookCacheService {
    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private BookDao bookDao;

    private static final String SINGLE_KEY = "BOOK_SINGLE";

    private static final String ALL_KEY = "BOOK_ALL";

    public List<Book> findBookByName(String name){
        RList<Book> bookRList = redissonClient.getList(ALL_KEY+"_"+name);
        if(!CollectionUtils.isEmpty(bookRList)){
            log.info("从缓存中缓存获取的数据个数:"+bookRList.size());
            return bookRList;
        }
        List<Book> books= bookDao.findByName(name);
        if(CollectionUtils.isEmpty(books)){
            log.info("数据库中查询不到数据");
            return null;
        }
        //添加到缓存中，60s后过期
        bookRList.addAll(books);
        bookRList.expire(60, TimeUnit.SECONDS);
        return bookRList;
    }

    public Book findBookById(Long id){
        RBucket<Book> bookRBucket =redissonClient.getBucket(SINGLE_KEY+"-"+id);
        if(bookRBucket!=null&&bookRBucket.get()!=null){
            log.info("从缓存中查找到的对象:"+bookRBucket.get().toString());
            return bookRBucket.get();
        }
        Book book=bookDao.findById(id);
        if(book==null){
            log.info("当前id无法查到数据:"+id);
            return null;
        }
        //添加到缓存中，60s后过期
        bookRBucket.set(book,60, TimeUnit.SECONDS);
        return bookRBucket.get();
    }

}
