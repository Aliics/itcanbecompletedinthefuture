package itcanbecompletedinthefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItCanBeCompletedInTheFutureTest {
    @Test
    void asynchronouslySupply() throws Exception {
        final String name = ItCanBeCompletedInTheFuture.asynchronouslySupply(() -> "Alex").getTheValue();

        assertEquals("Alex", name);
    }

    @Test
    void thenUseValueAndSupply() throws Exception {
        final int num = ItCanBeCompletedInTheFuture
                .asynchronouslySupply(() -> 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .thenUseValueAndSupply(n -> n + 1)
                .getTheValue();

        assertEquals(10, num);
    }

    @Test
    void flattenAndSupply() throws Exception {
        final String flattened = ItCanBeCompletedInTheFuture
                .asynchronouslySupply(() -> "foo")
                .flattenAndSupply(s -> ItCanBeCompletedInTheFuture.asynchronouslySupply(() -> s + "bar"))
                .getTheValue();

        assertEquals("foobar", flattened);
    }

    @Test
    void isLazyAndAsync() throws Exception {
        final AtomicReference<String> atomicName = new AtomicReference<>();

        final ItCanBeCompletedInTheFuture<String> lastNameAsync = ItCanBeCompletedInTheFuture.asynchronouslySupply(() ->
                atomicName.getAndSet(atomicName.get() + " Johnston")
        );
        final ItCanBeCompletedInTheFuture<String> firstNameAsync =
                ItCanBeCompletedInTheFuture.asynchronouslySupply(() -> atomicName.getAndSet("Alexander"));
        firstNameAsync.getTheValue();
        lastNameAsync.getTheValue();

        assertEquals("Alexander Johnston", atomicName.get());
    }
}
