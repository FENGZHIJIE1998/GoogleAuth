package com.dazhi.googleauth.modules.code.controller;


import com.dazhi.googleauth.modules.code.dto.CodeDTO;
import com.dazhi.googleauth.modules.code.entity.CodeEntity;
import com.dazhi.googleauth.modules.code.service.CodeService;
import com.dazhi.googleauth.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController()

public class CodeController {

    @Autowired
    private CodeService codeService;

    /**
     * 获取验证码列表
     * @return
     */
    @GetMapping("/listAll")
    public Result list(String token) {
        List<CodeEntity> list = codeService.list();
        return Result.ok(list);
    }

    /**
     * 新增验证码
     * @param codeDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CodeDTO codeDTO) {
        if (codeService.isExists(codeDTO.getName())) {
            return Result.build(400, "别名重复");
        }
        codeService.add(codeDTO);
        return Result.ok();
    }

    /**
     * 删除验证码
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {

        codeService.delete(id);
        return Result.ok();
    }


}


