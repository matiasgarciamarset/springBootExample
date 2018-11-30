package com.simple.base.project.api;

import com.simple.base.project.api.dto.PersonCreationDto;
import com.simple.base.project.api.dto.PersonDto;
import com.simple.base.project.repository.ApplicationRepository;
import com.simple.base.project.repository.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApplicationApi {

    ModelMapper mapper = new ModelMapper();

    @Autowired
    private ApplicationRepository productRepository;

    @GetMapping("/values")
    public List<PersonDto> ping(@RequestParam(value="name", required = false) String name) {
        if (name != null) {
            return productRepository.findByName(name).stream().map(v -> mapper.map(v, PersonDto.class)).collect(Collectors.toList());
        }
        return productRepository.findAll().stream().map(v -> mapper.map(v, PersonDto.class)).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public PersonDto create(@RequestBody PersonCreationDto create) {
        Person entity = mapper.map(create, Person.class);
        return mapper.map(productRepository.save(entity), PersonDto.class);
    }
}
