package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/28.
 */
public class StringLengthIllegalException extends Exception {

    public StringLengthIllegalException() {
        super();
    }

    public StringLengthIllegalException(String message) {
        super(message);
    }

    public StringLengthIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringLengthIllegalException(Throwable cause) {
        super(cause);
    }

    protected StringLengthIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
