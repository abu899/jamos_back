package jamos.back.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jamos.back.domain.user.User;
import jamos.back.domain.user.controller.form.UserRequestForm;
import jamos.back.domain.user.controller.form.UserResponseForm;
import jamos.back.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    void saveUser() throws Exception {
        given(userService.join((User)any()))
                .willReturn(
                        UserResponseForm.builder()
                                .id(1L)
                                .email("brettahn@abc.com")
                                .build().getId());

        String body = mapper.writeValueAsString(
                UserRequestForm.builder()
                        .email("brettahn@abc.com")
                        .name("brett")
                        .password("123")
                        .build()
        );

        ResultActions action = mvc.perform(
                post("/account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );

        action
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value("brettahn@abc.com"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findUser() {
    }
}