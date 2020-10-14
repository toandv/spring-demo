package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("toan");
        given(service.createUser(any())).willReturn(user);
        String json = "{\n" +
                "    \"name\": \"toan\"\n" +
                "}";
        try {
            ResultActions resultActions = mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json));
            Object result = resultActions.andExpect(jsonPath("id", is(1)));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
