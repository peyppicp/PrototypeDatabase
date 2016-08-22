package org.prototypeDatabase.exception;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class WhereRelationIllegalException extends Exception {

    public WhereRelationIllegalException() {
        super();
    }

    public WhereRelationIllegalException(String message) {
        super(message);
    }

    public WhereRelationIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public WhereRelationIllegalException(Throwable cause) {
        super(cause);
    }

    protected WhereRelationIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
