package com.adobe.aem.business;

import com.adobe.aem.model.NumberToRoman;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.adobe.aem.utils.Constants.VALUES_OF_ROMAN_SYMBOLS;
import static com.adobe.aem.utils.Constants.ROMAN_SYMBOLS;


/*
 * Business logic for API requests
 */
@Service
public class AemService {
    private static final Logger logger = LoggerFactory.getLogger(AemService.class);

    /***
     * computes Roman numeral equivalent to input number
     *
     * @param number
     * @return {@link NumberToRoman}
     */
    public NumberToRoman convertNumberToRoman(Integer number) throws Exception {
        logger.info("Converting numeral {} into roman", number);

        NumberToRoman responsePayload = new NumberToRoman();
        responsePayload.setInput(String.valueOf(number));

        StringBuilder romanNumeral = new StringBuilder();
        // check from higher value to lower value
        int index = 0;
        //iterate until the number becomes zero
        while (number > 0) {
            // check against the integer values in the VALUES_OF_ROMAN_SYMBOLS list
            while (number >= VALUES_OF_ROMAN_SYMBOLS[index]) {
                number -= VALUES_OF_ROMAN_SYMBOLS[index];
                // append corresponding Roman symbol
                romanNumeral.append(ROMAN_SYMBOLS[index]);
            }
            index++;
        }

        responsePayload.setOutput(romanNumeral.toString());
        logger.info("Numeral {} into roman {}", number, romanNumeral.toString());
        logger.info("Numeral {} into roman response payload {}", number, new ObjectMapper().writeValueAsString(responsePayload));

        return responsePayload;

    }

}
