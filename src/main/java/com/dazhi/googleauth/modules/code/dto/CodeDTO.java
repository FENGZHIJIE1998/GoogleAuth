package com.dazhi.googleauth.modules.code.dto;

import lombok.Data;

/**
 * 新增验证码传输类
 */
@Data
public class CodeDTO {

    private String name;
    private String secretKey;
}
