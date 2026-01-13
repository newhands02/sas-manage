package com.sas.entity;

import com.sas.enums.StatusEnum;
import lombok.Data;

/**
 * @author better
 */
@Data
public class Message {
    private String message;
    private String code;
    private Object data;
    public Message(String message, String code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
    public static Message success(Object data){
        return new Message(StatusEnum.SUCCESS.getMessage(), StatusEnum.SUCCESS.getCode(), data);
    }

    public static Message error(String message){
        return new Message(message, StatusEnum.FAIL.getCode(), null);
    }
    public static Message error(Exception e){
        return new Message(e.getMessage(), StatusEnum.FAIL.getCode(), null);
    }

}
