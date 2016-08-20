package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class DropDatabaseFaildException extends Exception {

    public DropDatabaseFaildException() {
        super();
    }

    public DropDatabaseFaildException(String message) {
        super(message);
    }

    public DropDatabaseFaildException(String message, Throwable cause) {
        super(message, cause);
    }

    public DropDatabaseFaildException(Throwable cause) {
        super(cause);
    }

    protected DropDatabaseFaildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
