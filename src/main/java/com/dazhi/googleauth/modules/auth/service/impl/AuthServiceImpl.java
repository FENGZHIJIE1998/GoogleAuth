package com.dazhi.googleauth.modules.auth.service.impl;

import com.dazhi.googleauth.modules.auth.dao.UserRepository;
import com.dazhi.googleauth.modules.auth.entity.UserEntity;
import com.dazhi.googleauth.modules.auth.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    //12小时后失效
    private final static int EXPIRE = 12;

    @Override
    public String createToken(UserEntity user) {
        //用UUID生成token
        String token = UUID.randomUUID().toString();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //保存到数据库
        user.setLoginTime(now);
        user.setExpireTime(expireTime);
        user.setToken(token);
        userRepository.save(user);
        return token;
    }

    @Override
    public void logout(String token) {
        UserEntity userEntity = userRepository.findByToken(token);
        //用UUID生成token
        token = UUID.randomUUID().toString();
        userEntity.setToken(token);
        userRepository.save(userEntity);

    }

    @Override
    public UserEntity findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
