package com.company.service;

import com.company.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public User fetchUserProfile(Long id){
        log.info("this is fetchUserProfile");
        User user = new User();
        user.setId(1L);
        user.setName("cms-provider-1");
        if(id.equals(user.getId())){
            return user;
        }
        return null;
    }
}
