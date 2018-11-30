package com.simple.base.project.api;

import com.simple.base.project.api.dto.PersonCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiHelper {

    @Autowired
    private MockMvc mvc;

    protected ResultActions createPerson(PersonCreationDto person) throws Exception {
        String person1 = "{\"name\":\""+person.getName()+"\"}";
        return mvc.perform(post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(person1))
                .andExpect(status().isOk());
    }
}
