package com.dazhi.googleauth.modules.code.service.impl;

import com.dazhi.googleauth.modules.code.dao.CodeRepository;
import com.dazhi.googleauth.modules.code.dto.CodeDTO;
import com.dazhi.googleauth.modules.code.entity.CodeEntity;
import com.dazhi.googleauth.modules.code.service.CodeService;
import com.dazhi.googleauth.common.utils.DynamicToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepository codeRepository;

    @Override
    public List<CodeEntity> list() {
        List<CodeEntity> all = codeRepository.findAll();
        return all;
    }


    @Override
    public void add(CodeDTO codeDTO) {
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setName(codeDTO.getName());
        codeEntity.setSecretKey(codeDTO.getSecretKey());
        codeEntity.setCode(getCode(codeEntity.getSecretKey()));
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime expire = now.plusSeconds(EXPIRE_TIME);
        //对 过期时间进行取整操作
        if (expire.getSecond() < 30) {
            // 过期时间小于30  置为0
            expire = LocalDateTime.of(expire.getYear(), expire.getMonth(), expire.getDayOfMonth(), expire.getHour(), expire.getMinute(), 0);
        } else {
            // 过期时间大于等于30 置为30
            expire = LocalDateTime.of(expire.getYear(), expire.getMonth(), expire.getDayOfMonth(), expire.getHour(), expire.getMinute(), 30);
        }
        codeEntity.setExpire(expire);
        codeRepository.save(codeEntity);
        System.out.println(codeEntity);

    }

    @Override
    public void delete(Integer id) {
        codeRepository.deleteById(id);

    }

    @Override
    public Boolean isExists(String name) {
        return codeRepository.existsByName(name);
    }


    /**
     * 过期时间30s
     */
    private final Long EXPIRE_TIME = 30L;

    /**
     * 定时任务，每分钟的0秒和30秒刷新数据库验证码
     */
    @Scheduled(cron = "0,30 * * * * ?")
    public void refreshAll() {
        List<CodeEntity> all = codeRepository.findAll();
        for (CodeEntity codeEntity : all) {
            codeEntity.setCode(getCode(codeEntity.getSecretKey()));
            LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(EXPIRE_TIME);
            codeEntity.setExpire(localDateTime);
            codeRepository.save(codeEntity);
        }
    }


    /**
     * 调用DynamicToken生成验证码
     *
     * @param secretKey
     * @return
     */
    private String getCode(String secretKey) {
        DynamicToken dt = new DynamicToken(secretKey);
        String dynamicCode = null;
        try {
            dynamicCode = dt.getDynamicCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dynamicCode;
    }
}
