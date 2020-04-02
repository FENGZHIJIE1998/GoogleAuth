package com.dazhi.googleauth.modules.auth.dao;


import com.dazhi.googleauth.modules.auth.entity.UserEntity;
import com.dazhi.googleauth.modules.code.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author CrazyJay
 * @Date 2019/3/30 22:05
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    UserEntity findByToken(String token);
}
