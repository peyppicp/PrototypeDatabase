package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class PropertiesNotFountException extends Exception {

    public PropertiesNotFountException() {
        super();
    }

    public PropertiesNotFountException(String message) {
        super(message);
    }

    public PropertiesNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesNotFountException(Throwable cause) {
        super(cause);
    }

    protected PropertiesNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
