package itcanbecompletedinthefuture;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ItCanBeCompletedInTheFuture<T> {
    private static final ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private final Thread thread;
    private T value;

    private ItCanBeCompletedInTheFuture(final Func0<T> f0) {
        thread = threadFactory.newThread(() -> value = f0.apply());
    }

    public static <T> ItCanBeCompletedInTheFuture<T> asynchronouslySupply(final Func0<T> f0) {
        return new ItCanBeCompletedInTheFuture<>(f0);
    }

    public ItCanBeCompletedInTheFuture<T> thenUseValueAndSupply(final Func1<T> f1) {
        return new ItCanBeCompletedInTheFuture<>(() -> {
            try {
                execOnThread();
            } catch (InterruptedException e) {
                throw new ItCouldNotBeCompletedInTheFutureException(e);
            }
            return f1.apply(value);
        });
    }

    public <R extends ItCanBeCompletedInTheFuture<?>> R flattenAndSupply(final FuncF<T, R> f) {
        try {
            return f.apply(getTheValue());
        } catch (InterruptedException e) {
            throw new ItCouldNotBeCompletedInTheFutureException(e);
        }
    }

    public T getTheValue() throws InterruptedException {
        execOnThread();
        return value;
    }

    private void execOnThread() throws InterruptedException {
        thread.start();
        thread.join();
    }
}
