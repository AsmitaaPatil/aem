package com.adobe.aem.business;

import com.adobe.aem.model.NumberToRoman;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class AemServiceTest {
    @InjectMocks
    private AemService aemService;

    @Before
    public void setUp() {
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
        NumberToRoman numberToRoman = aemService.convertNumberToRoman(input);
        assertTrue(mockedNumberToRoman.getOutput().equals(numberToRoman.getOutput()));

    }
}
