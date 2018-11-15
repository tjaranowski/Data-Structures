package pl.nosystems.java.datastructures.list;

@SuppressWarnings("unused")
public class DoubleLinkedListNode<T> {
    private DoubleLinkedListNode<T> previous;
    private T data;
    private DoubleLinkedListNode<T> next;

    public DoubleLinkedListNode(T data) {
        this.data = data;
    }

    public DoubleLinkedListNode(DoubleLinkedListNode<T> previous, T data, DoubleLinkedListNode<T> next) {
        this.previous = previous;
        this.data = data;
        this.next = next;
    }

    public DoubleLinkedListNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkedListNode<T> previous) {
        this.previous = previous;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoubleLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleLinkedListNode<T> next) {
        this.next = next;
    }
}
