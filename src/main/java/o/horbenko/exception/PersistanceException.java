package o.horbenko.exception;

public class PersistanceException extends Exception {

    public PersistanceException() {
    }

    public PersistanceException(String message) {
        super(message);
    }

    public PersistanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistanceException(Throwable cause) {
        super(cause);
    }

    public PersistanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
