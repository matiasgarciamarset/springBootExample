package com.simple.base.project.api;

import com.simple.base.project.api.dto.PersonCreationDto;
import com.simple.base.project.api.dto.SignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.ws.rs.core.MediaType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiHelper {

    @Autowired
    private MockMvc mvc;

    protected ResultActions signUp(SignupDto signup) throws Exception {
        String person1 = "{\"user\":\""+signup.getUser()+"\",\"password\":\""+signup.getPassword()+"\"}";
        return mvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(person1))
                .andExpect(status().isOk());
    }

    protected ResultActions createPerson(PersonCreationDto person) throws Exception {
        String token = getToken();
        String person1 = "{\"name\":\""+person.getName()+"\"}";
        return mvc.perform(post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Security", token)
                .content(person1))
                .andExpect(status().isOk());
    }

    private String getToken() throws Exception {
        SignupDto signup = new SignupDto();
        signup.setUser("admin");
        signup.setPassword("admin");
        Pattern p = Pattern.compile(".*\"token\":\\s*\"(.*)\".*");
        Matcher m = p.matcher(signUp(signup).andReturn().getResponse().getContentAsString());
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
