package com.gurpreet.exception;

import com.gurpreet.enums.ErrorCode;
import com.gurpreet.enums.ErrorMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode (callSuper = false)
public class BondManagerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    private String message;

    private BondManagerException(Exception e) {
        super(e.getMessage(), e);
        errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        message = e.getMessage();
    }

    public BondManagerException(Exception e, ErrorCode errorCode) {
        this(e);
        this.errorCode = errorCode;
        message = e.getMessage();
    }

    public BondManagerException(String message, ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BondManagerException(Exception e, ErrorCode errorCode, String message) {
        this(e);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BondManagerException(Exception e, ErrorCode errorCode, ErrorMessage errorMessage) {
        this(e);
        this.errorCode = errorCode;
        this.message = errorMessage.getMessage();
    }

    public BondManagerException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BondManagerException(ErrorCode errorCode, ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorCode = errorCode;
        this.message = errorMessage.getMessage();
    }

}
