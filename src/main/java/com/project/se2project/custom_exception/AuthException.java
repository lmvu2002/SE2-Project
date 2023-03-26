package com.project.se2project.custom_exception;

import org.springframework.security.core.AuthenticationException;
import java.io.Serial;

public class AuthException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 1;

    public AuthException(String msg) {
        super(msg);
    }
}
