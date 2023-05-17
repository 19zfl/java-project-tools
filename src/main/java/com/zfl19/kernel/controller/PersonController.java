package com.zfl19.kernel.controller;

import com.zfl19.configs.result.JSONResult;
import com.zfl19.kernel.domain.Person;
import com.zfl19.kernel.service.IPersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 临时控制层：PersonController
 * @author 19zfl
 * @date 2023/5/18
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    /**
     * 依赖注入
     */
    @Resource
    private IPersonService personService;

    /**
     * 查询所有方法
     */
    @GetMapping(value = "/queryAll")
    public JSONResult selectAllPersonInfo() {
        List<Person> people = personService.selectAllPersonInfo();
        return JSONResult.success(people);
    }

}
