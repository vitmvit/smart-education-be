package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.model.dto.auth.PasswordDto;
import com.vitmvit.smarteducation.model.dto.auth.SignInDto;
import com.vitmvit.smarteducation.model.dto.auth.SignUpDto;
import com.vitmvit.smarteducation.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/application-test.properties")
@Sql(value = "/sql/before_auth_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/after_auth_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loginTest() throws Exception {
        SignInDto signInDto;
        // positive
        signInDto = new SignInDto("user@localhost", "user@localhost");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/login")
                        .content(TestUtils.objectToJson(signInDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        // negative
        signInDto = new SignInDto("1234@localhost", "1234@localhost");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/login")
                        .content(TestUtils.objectToJson(signInDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        // negative
        signInDto = new SignInDto("user@localhost", "");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/login")
                        .content(TestUtils.objectToJson(signInDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        // negative
        signInDto = new SignInDto("", "user@localhost");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/login")
                        .content(TestUtils.objectToJson(signInDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        // negative
        signInDto = new SignInDto("", "");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/login")
                        .content(TestUtils.objectToJson(signInDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        // negative
        signInDto = new SignInDto("", "");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/login")
                        .content(TestUtils.objectToJson(signInDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void registerTest() throws Exception {
        SignUpDto signUpDto;
        PasswordDto passwordDto;
        // positive
        signUpDto = new SignUpDto("user", "user", "user", "+375291234567", 291234567, "user@localhost", new PasswordDto("12345678", "12345678"));
    }
}
