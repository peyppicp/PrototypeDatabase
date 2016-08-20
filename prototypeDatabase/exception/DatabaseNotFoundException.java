package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class DatabaseNotFoundException extends Exception {
    public DatabaseNotFoundException() {
        super();
    }

    public DatabaseNotFoundException(String message) {
        super(message);
    }

    public DatabaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DatabaseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
