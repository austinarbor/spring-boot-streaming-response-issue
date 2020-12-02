package dev.aga.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class SomeControllerTest {
    private static final String BASE_URI = "/api/";

    @Autowired
    private MockMvc mvc;

    @Test
    void testDownload1() {
        try {
            MvcResult mvcResult = mvc.perform(get(BASE_URI + "download1"))
                    .andExpect(request().asyncStarted())
                    .andReturn();

            mvc.perform(asyncDispatch(mvcResult))
                    .andExpect(status().isBadGateway())
                    .andExpect(jsonPath("$.error").value("Illegal Argument"));

        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testDownload2() {
        try {
            MvcResult mvcResult = mvc.perform(get(BASE_URI + "download2"))
                    .andExpect(request().asyncStarted())
                    .andReturn();

            mvc.perform(asyncDispatch(mvcResult))
                    .andExpect(status().isConflict())
                    .andExpect(status().reason("Illegal state!"));

        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testDownload3() {
        try {
            MvcResult mvcResult = mvc.perform(get(BASE_URI + "download3"))
                    .andExpect(request().asyncStarted())
                    .andReturn();

            mvc.perform(asyncDispatch(mvcResult))
                    .andExpect(status().isBadGateway())
                    .andExpect(jsonPath("$.error").value("Illegal Argument"));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testDownload4() {
        try {
            MvcResult mvcResult = mvc.perform(get(BASE_URI + "download4"))
                    .andExpect(request().asyncStarted())
                    .andReturn();

            mvc.perform(asyncDispatch(mvcResult))
                    .andExpect(status().isConflict())
                    .andExpect(status().reason("Illegal state!"));
        } catch (Exception e) {
            fail(e);
        }
    }
}
