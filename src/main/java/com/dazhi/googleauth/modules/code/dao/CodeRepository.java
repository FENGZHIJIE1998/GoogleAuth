package com.dazhi.googleauth.modules.code.dao;


import com.dazhi.googleauth.modules.code.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author CrazyJay
 * @Date 2019/3/30 22:05
 * @Version 1.0
 */
public interface CodeRepository extends JpaRepository<CodeEntity, Integer> {
    Boolean existsByName(String name);
}
