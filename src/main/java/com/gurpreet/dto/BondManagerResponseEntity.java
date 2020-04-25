package com.gurpreet.dto;

import com.gurpreet.enums.ErrorCode;
import com.gurpreet.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BondManagerResponseEntity extends ResponseEntity<ResponseWrapper> {

    private static final String SUCCESS_DEFAULT_PAYLOAD = "success";

    private BondManagerResponseEntity(ResponseWrapper body, HttpStatus status) {
        super(body, status);
    }

    public static BondManagerResponseEntity buildSuccessResponse(Object payload) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setHttpCode(HttpStatus.OK.value());
        responseWrapper.setPayload(payload);
        responseWrapper.setStatus(ResponseWrapper.Status.OK);
        return new BondManagerResponseEntity(responseWrapper, HttpStatus.OK);
    }

    public static BondManagerResponseEntity buildSuccessResponse() {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setHttpCode(HttpStatus.OK.value());
        responseWrapper.setPayload(SUCCESS_DEFAULT_PAYLOAD);
        responseWrapper.setStatus(ResponseWrapper.Status.OK);
        return new BondManagerResponseEntity(responseWrapper, HttpStatus.OK);
    }

    public static BondManagerResponseEntity buildErrorResponse(ErrorCode errorCode, String message) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setHttpCode(errorCode.getCode());
        responseWrapper.setMessage(message);
        responseWrapper.setStatus(ResponseWrapper.Status.ERROR);
        return new BondManagerResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    public static BondManagerResponseEntity buildValidationErrorResponse(ErrorCode errorCode, String message) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setHttpCode(errorCode.getCode());
        responseWrapper.setMessage(message);
        responseWrapper.setStatus(ResponseWrapper.Status.ERROR);
        return new BondManagerResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
    }

}
