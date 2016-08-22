package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class WhereStatementNotFoundException extends Exception {

    public WhereStatementNotFoundException() {
        super();
    }

    public WhereStatementNotFoundException(String message) {
        super(message);
    }

    public WhereStatementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WhereStatementNotFoundException(Throwable cause) {
        super(cause);
    }

    protected WhereStatementNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
