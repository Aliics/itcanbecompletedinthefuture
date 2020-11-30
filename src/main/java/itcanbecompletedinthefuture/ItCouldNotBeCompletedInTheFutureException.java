package itcanbecompletedinthefuture;

public class ItCouldNotBeCompletedInTheFutureException extends RuntimeException {
    public ItCouldNotBeCompletedInTheFutureException() {
        super();
    }

    public ItCouldNotBeCompletedInTheFutureException(final String message) {
        super(message);
    }

    public ItCouldNotBeCompletedInTheFutureException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ItCouldNotBeCompletedInTheFutureException(final Throwable cause) {
        super(cause);
    }

    protected ItCouldNotBeCompletedInTheFutureException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
