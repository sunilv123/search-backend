package com.assignment.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class GenericResponse {

    private ResponseStatus status;

    private Object data;


    public GenericResponse(int code, Object data) {

        this.status = new ResponseStatus(code, null);
        this.data = data;

    }

    public GenericResponse(int code, String message) {

        this.status = new ResponseStatus(code, message);

    }

    public GenericResponse(int code) {

        this.status = new ResponseStatus(code, null);

    }

    public GenericResponse(HttpStatus code, Object data) {

        this.status = new ResponseStatus(code.value(), null);
        this.data = data;

    }

}
