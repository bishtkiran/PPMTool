package com.learning.personalProjectManagementTool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends Exception{

    public ProjectIdException(String message) {
        super(message);
    }
}
