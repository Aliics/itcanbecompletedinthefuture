package itcanbecompletedinthefuture;

public interface FuncF<G, R extends ItCanBeCompletedInTheFuture> {
    R apply(G f);
}
