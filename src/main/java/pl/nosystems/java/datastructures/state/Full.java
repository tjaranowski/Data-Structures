package pl.nosystems.java.datastructures.state;

@SuppressWarnings("WeakerAccess")
public class Full extends Exception {
    public Full() {}

    public Full(Throwable throwable) {
        super(throwable);
    }
}
