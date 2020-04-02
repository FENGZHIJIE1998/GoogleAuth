package com.dazhi.googleauth.modules.code.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 验证码实体类
 */
@Data
@Entity
public class CodeEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * name 别名
     */
    private String name;
    /**
     * code 验证码
     */
    private String code;
    /**
     * expire 过期时间
     */
    private LocalDateTime expire;

    /**
     * secretKey 密钥
     */
    private String secretKey;
}
