package pl.nosystems.java.datastructures.state;

@SuppressWarnings("WeakerAccess")
public class Empty extends Exception {
    public Empty() {}

    public Empty(Throwable throwable) {
        super(throwable);
    }
}
