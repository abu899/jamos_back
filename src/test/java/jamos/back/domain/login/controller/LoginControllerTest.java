package jamos.back.domain.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jamos.back.domain.login.controller.form.LoginRequestForm;
import jamos.back.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void login() throws Exception {
//        User user = new User("brettahn@abc.com", "123");
//        userRepository.save(user);

        String body = mapper.writeValueAsString(
                LoginRequestForm.builder()
                        .email("brettahn@abc.co")
                        .password("123")
                        .build()
        );

        ResultActions action = mvc.perform(
                post("/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );

        action
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void logoutWithSession() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();

        ResultActions action = mvc.perform(
                post("/logout")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .session(mockHttpSession)
        );

        action
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void logoutWithoutSession() throws Exception {

        ResultActions action = mvc.perform(
                post("/logout")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );

        action
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}