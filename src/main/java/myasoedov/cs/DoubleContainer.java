package myasoedov.cs;

public class DoubleContainer<A, B> {
    private A first;
    private B second;

    public DoubleContainer() {
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}
