package com.sky;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReadingApplication.class)
@WebAppConfiguration
public class MockMvcWebTests {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mock;

    @Before
    public  void setupMock(){
        mock= MockMvcBuilders.webAppContextSetup(webContext).build();

    }

    @Test
    public void postbook() throws Exception {
        mock.perform(post("/readinglist")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title","Book title")
                .param("author","xjjs")
                .param("Isbn","2552562")
                .param("description","hjshsks"));
    }

    @Test
    public void homepage() throws Exception{
        mock.perform(get("/readinglist/jjj"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("readinglist"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"));

    }




}
