package com.exception;

/**
 * This exception is thrown when an account does not exists for given app secret.
 */
public class ApplicationAuthorizationException extends Exception {

    public ApplicationAuthorizationException(String s) {
        super(s);
    }
}
