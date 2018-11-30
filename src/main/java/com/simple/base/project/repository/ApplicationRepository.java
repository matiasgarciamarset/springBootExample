package com.simple.base.project.repository;

import com.simple.base.project.repository.dao.PersonDao;
import com.simple.base.project.repository.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRepository {

    private final PersonDao productRepository;

    ApplicationRepository(final PersonDao productRepository) {
        this.productRepository = productRepository;
    }

    public List<Person> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Person> findAll() {
        return productRepository.findAll();
    }

    public Person save(Person entity) {
        return productRepository.save(entity);
    }
}
