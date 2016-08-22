package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class OperationNotIllegalException extends Exception {

    public OperationNotIllegalException() {
        super();
    }

    public OperationNotIllegalException(String message) {
        super(message);
    }

    public OperationNotIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotIllegalException(Throwable cause) {
        super(cause);
    }

    protected OperationNotIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
