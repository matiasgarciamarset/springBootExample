package com.simple.base.project.repository.dao;

import com.simple.base.project.repository.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Queries structure: https://docs.spring.io/spring-data/jpa/docs/2.0.0.RC2/reference/html/#repositories.query-methods.query-creation
 */
@Transactional
public interface PersonDao extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
}
