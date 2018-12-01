package com.simple.base.project.repository;

import com.simple.base.project.repository.dao.AuthenticationDao;
import com.simple.base.project.repository.dao.PersonDao;
import com.simple.base.project.repository.model.Authentication;
import com.simple.base.project.repository.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRepository {

    private final PersonDao productDao;

    private final AuthenticationDao authenticationDao;

    ApplicationRepository(final PersonDao productDao, final AuthenticationDao authenticationDao) {
        this.productDao = productDao;
        this.authenticationDao = authenticationDao;
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

    public Authentication save(Authentication entity) {
        return authenticationDao.save(entity);
    }

    public Authentication findFirstByToken(String token) {
        return authenticationDao.findFirstByToken(token);
    }
}
