package com.adobe.aem.controller;

import com.adobe.aem.business.AemService;
import com.adobe.aem.handler.AemAdvice;
import com.adobe.aem.model.ErrorResponse;
import com.adobe.aem.model.NumberToRoman;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class AemControllerTest {


    @InjectMocks
    private AemController aemController;

    @Mock
    private AemService aemService;

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(aemController)
                .setControllerAdvice(new AemAdvice())
                .build();

        MockitoAnnotations.initMocks(this);
    }

    /*
    Valid number to roman numeral test
    */
    @Test
    public void testValidNumeral() throws Exception {
        Integer input = 3;
        NumberToRoman mockedNumberToRoman = new NumberToRoman();
        mockedNumberToRoman.setInput(String.valueOf(input));
        mockedNumberToRoman.setOutput("III");

        when(aemService.convertNumberToRoman(input)).thenReturn(mockedNumberToRoman);
        ResponseEntity<Object> successResponse = aemController.numeralToRoman(input);
        assertEquals(HttpStatus.OK, successResponse.getStatusCode());
        assert successResponse.getBody().equals(mockedNumberToRoman);

    }

    /*
    Integration test to test response in case of an exception
    */
    @Test
    public void testException() throws Exception {

        Mockito.doThrow(new Exception("any")).when(aemService).convertNumberToRoman(7);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/romannumeral?query=7");

        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.errorMessage").value("Something went wrong. Please try again later or contact support"));

    }

}
