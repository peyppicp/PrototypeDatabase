package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class TableNotFoundException extends Exception {
    public TableNotFoundException() {
        super();
    }

    public TableNotFoundException(String message) {
        super(message);
    }

    public TableNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TableNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
