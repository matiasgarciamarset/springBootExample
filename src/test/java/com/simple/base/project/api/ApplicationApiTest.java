package com.simple.base.project.api;

import com.simple.base.project.Application;
import com.simple.base.project.api.dto.PersonCreationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = Application.class)
@AutoConfigureMockMvc
public class ApplicationApiTest extends ApiHelper {

	@Test
	public void testCreatePerson_returnsId() throws Exception {
		PersonCreationDto person = new PersonCreationDto();
		person.setName("test");
		createPerson(person)
				.andExpect(jsonPath("$.id", is(1)));
	}
}
