package com.zfl19.kernel.mapper;

import com.zfl19.kernel.domain.Person;

import java.util.List;

/**
 * 临时持久层：PersonMapper
 */
public interface PersonMapper {

    List<Person> selectAllPersonInfo();

}
