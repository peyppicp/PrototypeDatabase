package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class AlreadyExistDatabaseException extends Exception {

    public AlreadyExistDatabaseException() {
        super();
    }

    public AlreadyExistDatabaseException(String message) {
        super(message);
    }

    public AlreadyExistDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistDatabaseException(Throwable cause) {
        super(cause);
    }

    protected AlreadyExistDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
