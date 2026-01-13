package com.sas.enums;

/**
 * @author 86187
 */

public enum StatusEnum {
    SUCCESS("0", "SUC0000"), FAIL("-1", "ERR0001");
    private String code;
    private String message;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
