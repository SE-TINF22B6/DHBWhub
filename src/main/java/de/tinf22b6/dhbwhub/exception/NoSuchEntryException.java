package de.tinf22b6.dhbwhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchEntryException extends RuntimeException {
    public NoSuchEntryException(String message) {
        super(message);
    }
}
