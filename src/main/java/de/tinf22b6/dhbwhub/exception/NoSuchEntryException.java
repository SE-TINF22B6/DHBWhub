package de.tinf22b6.dhbwhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchEntryException extends RuntimeException {
    public NoSuchEntryException(String objectName, Long id) {
        super(String.format("%s with ID %d does not exist", objectName, id));
    }
}
