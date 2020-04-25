package com.gurpreet.controller;

import com.gurpreet.dto.BondManagerResponseEntity;
import com.gurpreet.enums.ErrorCode;
import com.gurpreet.request.AddBondRequest;
import com.gurpreet.request.DeleteBondRequest;
import com.gurpreet.request.SellBondRequest;
import com.gurpreet.service.BondService;
import com.gurpreet.validator.BondValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/bond")
public class BondController {

    @Autowired
    private BondService bondService;

    @PostMapping("add")
    public BondManagerResponseEntity addBond(@NotNull @RequestBody AddBondRequest request) {
        try {
            if(!BondValidator.validBond(request)) {
                return BondManagerResponseEntity.buildValidationErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
            }
            return BondManagerResponseEntity.buildSuccessResponse(bondService.addBond(request));
        } catch (com.gurpreet.exception.BondManagerException e) {
            log.error("Exception occurred in createUser due to: {}", e.getMessage(), e);
            return BondManagerResponseEntity.buildErrorResponse(e.getErrorCode(), e.getMessage());
        }
    }

    @PostMapping("delete")
    public BondManagerResponseEntity deleteBond(@NotNull @RequestBody DeleteBondRequest request) {
        try {
            if(!BondValidator.validDeleteBondRequest(request)) {
                return BondManagerResponseEntity.buildValidationErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
            }
            bondService.deleteBond(request);
            return BondManagerResponseEntity.buildSuccessResponse();
        } catch (com.gurpreet.exception.BondManagerException e) {
            log.error("Exception occurred in deleteBond due to: {}", e.getMessage(), e);
            return BondManagerResponseEntity.buildErrorResponse(e.getErrorCode(), e.getMessage());
        }
    }

    @PostMapping("sell")
    public BondManagerResponseEntity sellBond(@NotNull @RequestBody SellBondRequest request) {
        try {
            if(!BondValidator.validSellBondRequest(request)) {
                return BondManagerResponseEntity.buildValidationErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
            }
            bondService.sellBond(request);
            return BondManagerResponseEntity.buildSuccessResponse();
        } catch (com.gurpreet.exception.BondManagerException e) {
            log.error("Exception occurred in deleteBond due to: {}", e.getMessage(), e);
            return BondManagerResponseEntity.buildErrorResponse(e.getErrorCode(), e.getMessage());
        }
    }

    @GetMapping("customerDetailsByBondId")
    public BondManagerResponseEntity getCustomersForBondId(@NotNull Integer bondId) {
        try {
            if(bondId == null) {
                return BondManagerResponseEntity.buildValidationErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
            }
            return BondManagerResponseEntity.buildSuccessResponse(bondService.getCustomersForBondId(bondId));
        } catch (com.gurpreet.exception.BondManagerException e) {
            log.error("Exception occurred in getCustomersForBondId due to: {}", e.getMessage(), e);
            return BondManagerResponseEntity.buildErrorResponse(e.getErrorCode(), e.getMessage());
        }
    }

    @GetMapping("getBondsForCustomer")
    public BondManagerResponseEntity getBondsForCustomer(@NotNull Integer userId) {
        try {
            if(userId == null) {
                return BondManagerResponseEntity.buildValidationErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
            }
            return BondManagerResponseEntity.buildSuccessResponse(bondService.getBondsForCustomer(userId));
        } catch (com.gurpreet.exception.BondManagerException e) {
            log.error("Exception occurred in getCustomersForBondId due to: {}", e.getMessage(), e);
            return BondManagerResponseEntity.buildErrorResponse(e.getErrorCode(), e.getMessage());
        }
    }




}
