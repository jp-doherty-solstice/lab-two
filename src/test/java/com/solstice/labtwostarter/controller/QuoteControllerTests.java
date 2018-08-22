package com.solstice.labtwostarter.controller;

import com.solstice.labtwostarter.service.QuoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
public class QuoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @InjectMocks
    private QuoteController quoteController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetData() throws Exception {

        mockMvc.perform(get("/GOOG/2018-06-22"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testGetMonthlyData() throws Exception {

        mockMvc.perform(get("/GOOG/2018-06-22/monthly"))
                .andExpect(status().isOk())
                .andReturn();

    }


}
