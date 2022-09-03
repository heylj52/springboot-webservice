package com.heylj.book.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@ExtendWith(SpringExtension.class) //Junit5에서 이걸로 바뀜(@RunWith(...))
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void hello가_리턴된다() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 get요청을 함
                .andExpect(status().isOk()) //결과를 검증. 200인지 검증
                .andExpect(content().string(hello)); //return 값 검증
    }

    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));

    }
}
