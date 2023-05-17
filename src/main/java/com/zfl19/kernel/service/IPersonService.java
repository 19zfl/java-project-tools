package com.zfl19.kernel.service;

import com.zfl19.kernel.domain.Person;

import java.util.List;

/**
 * 临时业务层：IPersonService
 */
public interface IPersonService {

    List<Person> selectAllPersonInfo();

}
