package com.dazhi.googleauth.modules.code.service;


import com.dazhi.googleauth.modules.code.dto.CodeDTO;
import com.dazhi.googleauth.modules.code.entity.CodeEntity;

import java.util.List;

public interface CodeService {
    /**
     * 列出所有验证码
     * @return
     */
    List<CodeEntity> list();

    /**
     * 新增验证码
     * @param codeDTO
     */
    void add(CodeDTO codeDTO);

    /**
     * 删除验证码
     * @param id
     */
    void delete(Integer id);

    /**
     * 判断验证码是否存在
     * @param name
     * @return
     */
    Boolean isExists(String name);
}
