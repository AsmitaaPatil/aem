package com.adobe.aem.controller;

import com.adobe.aem.business.AemService;
import com.adobe.aem.model.NumberToRoman;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * Entry point for any request mapped to its appropriate methods
 */

@Validated
@RestController
@RequestMapping("/")
public class AemController {

    private static Logger logger = LoggerFactory.getLogger(AemController.class);

    @Autowired
    AemService aemService;

    /***
     * serves GET request to return Roman numeral equivalent of input number
     *
     * @param number
     * @return {@link NumberToRoman}
     */
    @GetMapping("/romannumeral")
    public ResponseEntity<Object> numeralToRoman(
            @Valid @NotNull
            @Min(value = 1, message = "Invalid input parameters: input numeral must be in the range 1 to 3999")
            @Max(value = 3999, message = "Invalid input parameters: input numeral must be in the range 1 to 3999")
            @RequestParam(name = "query") Integer number) throws Exception {


        logger.info("Request received to convert numeral {} to roman", number);
        NumberToRoman romanValue = aemService.convertNumberToRoman(number);

        logger.info("Response received to convert numeral {} to roman as {}", number, new ObjectMapper().writeValueAsString(romanValue));
        return new ResponseEntity<>(romanValue, HttpStatus.OK);

    }

}
