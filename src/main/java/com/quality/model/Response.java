package com.quality.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    /**
     * response status
     */
    private Boolean success;
    /**
     * response code
     */
    private Integer code = 200;
    /**
     * response message
     */
    private String message;
    /**
     * response data
     */
    private T data;
}