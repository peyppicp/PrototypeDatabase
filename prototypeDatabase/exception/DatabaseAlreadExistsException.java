package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class DatabaseAlreadExistsException extends Exception {

    public DatabaseAlreadExistsException() {
        super();
    }

    public DatabaseAlreadExistsException(String message) {
        super(message);
    }

    public DatabaseAlreadExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseAlreadExistsException(Throwable cause) {
        super(cause);
    }

    protected DatabaseAlreadExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
