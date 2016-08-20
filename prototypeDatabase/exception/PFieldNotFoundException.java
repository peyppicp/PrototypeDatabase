package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class PFieldNotFoundException extends Exception {
    public PFieldNotFoundException() {
        super();
    }

    public PFieldNotFoundException(String message) {
        super(message);
    }

    public PFieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PFieldNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PFieldNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
