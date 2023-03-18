package com.eh.gc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eh.gc.exception.ErrorResponse;
import com.eh.gc.helper.constants.TestConstants;
import com.eh.gc.helper.message.CommonMessageConstants;
import com.eh.gc.helper.message.TestMessageConstants;
import com.eh.gc.payload.ApiResponse;
import com.eh.gc.service.IService.ITestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * This controller is to provide test api
 *
 * @author MD. EMRAN HOSSAIN
 * @since 17 MAR, 2023
 * @version 1.1
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
//@RequestMapping(TestConstants.ROOT)
@RestController
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    ITestService iTestService;

    @Operation(summary = TestConstants.TEST_SWAGGER_SUMMARY, description = TestConstants.TEST_SWAGGER_DESCRIPTION)
    @ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = TestMessageConstants.API_CHECK_EN,
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = TestController.class)))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = TestMessageConstants.API_FAILD_CHECKED_EN,
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
            })
    @GetMapping(TestConstants.TEST_API_ENDPOINT)
    public ResponseEntity<?> getTest(){
        try {
            Object getTestResponse = iTestService.getTest();
            LOG.info(TestMessageConstants.API_CHECK_EN);
            return ResponseEntity.ok(new ApiResponse(true, CommonMessageConstants.DATA_FOUND_EN, CommonMessageConstants.DATA_FOUND_BN, getTestResponse, 1L));
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return new ResponseEntity(new ErrorResponse(false, CommonMessageConstants.SOMTHING_WRONG_EN, CommonMessageConstants.SOMTHING_WRONG_BN, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
