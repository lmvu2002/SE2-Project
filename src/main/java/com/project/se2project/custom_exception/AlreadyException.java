package com.project.se2project.custom_exception;

import org.springframework.security.core.AuthenticationException;

public class AlreadyException extends AuthenticationException {
    public AlreadyException(String msg) {
        super(msg);
    }
}
