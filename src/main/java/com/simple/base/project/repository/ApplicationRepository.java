package com.simple.base.project.repository;

import com.simple.base.project.repository.dao.PersonDao;
import com.simple.base.project.repository.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRepository {

    private final PersonDao productDao;

    ApplicationRepository(final PersonDao productDao) {
        this.productDao = productDao;
    }

    public List<Person> findByName(String name) {
        return productDao.findByName(name);
    }

    public List<Person> findAll() {
        return productDao.findAll();
    }

    public Person save(Person entity) {
        return productDao.save(entity);
    }
}
