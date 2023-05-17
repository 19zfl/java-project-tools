package com.zfl19.kernel.service.impl;

import com.zfl19.kernel.domain.Person;
import com.zfl19.kernel.service.IPersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 临时业务层实现类：PersonServiceImpl
 * @author 19zfl
 * @date 2023/5/18
 */
@Service
public class PersonServiceImpl implements IPersonService {
    @Override
    public List<Person> selectAllPersonInfo() {
        return new ArrayList<Person>();
    }
}
