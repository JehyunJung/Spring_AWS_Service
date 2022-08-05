package web_service.springawsserver.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import web_service.springawsserver.config.auth.SecurityConfig;
import web_service.springawsserver.web.controller.HelloController;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("기본 컨트롤러 테스트")
    void hello() throws Exception {
        String hello = "Hello";

        //Http GET에 대한 요청 점검
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //Http Status에 대한 점검
                .andExpect(content().string(hello)); //Http Response의 content 내용 검증

    }

    @Test
    @DisplayName("Dto class 테스트")
    void helloDto() throws Exception {

        String name = "Test";
        int amount = 1000;

        //Http GET에 대한 요청 점검
        mvc.perform(get("/hello/dto")
                        .param("name", name)//param 메소드를 이용해서 Request 객체에 Query String, parameter 형태로 등록할 수 있다.
                        .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk()) //Http Status에 대한 점검

                .andExpect(jsonPath("$.name", is(name))) //Http Response의 content 내용 검증
                .andExpect(jsonPath("$.amount", is(amount))); //Http Response의 content 내용 검증
    }
}