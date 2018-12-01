package com.simple.base.project.api;

import com.simple.base.project.api.dto.SignupDto;
import com.simple.base.project.api.dto.PersonCreationDto;
import com.simple.base.project.api.dto.PersonDto;
import com.simple.base.project.api.dto.SignupResponseDto;
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
    private TokenService tokenService;

    @Autowired
    private ApplicationRepository productRepository;

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignupDto login) {
        String token = tokenService.createToken(login.getUser(), login.getPassword());
        return new SignupResponseDto(login.getUser(), token);
    }

    @GetMapping("/values")
    public List<PersonDto> values(@RequestParam(value="name", required = false) String name, @RequestHeader(name="Security") String token) {
        if (!tokenService.isTokenValid(token)) {
            return null;
        }
        if (name != null) {
            return productRepository.findByName(name).stream().map(v -> mapper.map(v, PersonDto.class)).collect(Collectors.toList());
        }
        return productRepository.findAll().stream().map(v -> mapper.map(v, PersonDto.class)).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public PersonDto create(@RequestBody PersonCreationDto create, @RequestHeader(name="Security") String token) {
        if (!tokenService.isTokenValid(token)) {
            return null;
        }
        Person entity = mapper.map(create, Person.class);
        return mapper.map(productRepository.save(entity), PersonDto.class);
    }
}
