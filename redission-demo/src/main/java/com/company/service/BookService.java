package com.company.service;

import com.company.dao.BookDao;
import com.company.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RedissonClient redissonClient;

    private static String LOCK_KEY = "LOCK_KEY";

    public List<Book> searchBooksByName(String name){
       return bookDao.findByName(name);
    }

    public Book searchBookByID(Long id){
        return bookDao.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBook(Book book) throws InterruptedException {
        //基于redisson实现分布式锁
        if (book==null || book.getId()==null){
            return Boolean.FALSE;
        }
        RLock rLock = null;
        boolean result =false;
        try {
            rLock = redissonClient.getLock(LOCK_KEY+"_"+book.getId());
            if (rLock.tryLock(1l,3l, TimeUnit.SECONDS)){
                log.info("当前线程获取到锁:线程ID:"+Thread.currentThread().getId());
                result = bookDao.updateBook(book)==1;
                Thread.sleep(1*1000);
                rLock.unlock();
                log.info("当前线程解锁:线程ID:"+Thread.currentThread().getId());
                return result;
            }
            log.info("当前线未获取到锁:线程ID:"+Thread.currentThread().getId());
        }finally {
            if (rLock!=null && rLock.isLocked() && rLock.isHeldByCurrentThread()){
                log.info("当前线程尝试解锁:线程ID:"+Thread.currentThread().getId());
                rLock.unlock();
            }
        }
        return false;
    }
}
