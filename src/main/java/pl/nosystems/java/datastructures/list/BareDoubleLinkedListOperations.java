package pl.nosystems.java.datastructures.list;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class BareDoubleLinkedListOperations {
    private BareDoubleLinkedListOperations() {}

    private static <T> void addFirstElement(final BareDoubleLinkedList<T> list,final T data) { 
        final BareDoubleLinkedList.Node newNode = new BareDoubleLinkedList.Node<>(data);
        list.setLastNode(newNode);
        list.setFirstNode(newNode);
    }
    
    private static <T> void addNextElement(final BareDoubleLinkedList<T> list,final T data) {
        final BareDoubleLinkedList.Node newNode = new BareDoubleLinkedList.Node<>(list.getLastNode(),data,null);
        list.getLastNode().setNext(newNode);
        list.setLastNode(newNode);
    }

    private static <T> void removeNodeThatHasOnlyNext(final BareDoubleLinkedList<T> list,final BareDoubleLinkedList.Node node, final BareDoubleLinkedList.Node next) {
        next.setPrevious(null);
        next.setNext(null);
        list.setFirstNode(next);
    }

    private static <T> void removeNodeThatHasOnlyPrevious(final BareDoubleLinkedList<T> list,final BareDoubleLinkedList.Node node, final BareDoubleLinkedList.Node prev) {
        list.setLastNode(list.getLastNode().getPrevious());

        prev.setNext(null);
        node.setPrevious(null);
    }

    private static <T> void removeNodeThatIsInMiddle(final BareDoubleLinkedList.Node prev, final BareDoubleLinkedList.Node next) {
        prev.setNext(next);
        next.setPrevious(prev);
    }

    private static <T> void removeNode(final BareDoubleLinkedList<T> list,final BareDoubleLinkedList.Node node) {
        final BareDoubleLinkedList.Node prev = node.getPrevious();
        final BareDoubleLinkedList.Node next = node.getNext();

        if(prev == null) {
            removeNodeThatHasOnlyNext(list,node,next);

        } else if(next == null) {
            removeNodeThatHasOnlyPrevious(list,node,prev);

        } else {
            removeNodeThatIsInMiddle(prev,next);

        }
    }

    private static <T> void removeLastNodeInList(final BareDoubleLinkedList<T> list) {
        list.setFirstNode(null);
        list.setLastNode(null);
    }

    public static <T> void add(final BareDoubleLinkedList<T> list,final T data) {
        if(list.getFirstNode() == null) {
            addFirstElement(list,data);
        } else {
            addNextElement(list,data);
        }
    }

    public static <T> boolean removeFirst(final BareDoubleLinkedList<T> list,final T data) {
        if(list.getFirstNode() == null) {
            return false;
        }

        if(list.getFirstNode().equals(list.getLastNode())) {
            if(list.getFirstNode().getData().equals(data)) {
                removeLastNodeInList(list);
                return true;
            }
            return false;
        }

        BareDoubleLinkedList.Node currentNode = list.getFirstNode();
        while(currentNode != null) {
            if(currentNode.getData() == null) {
                if(data == null) {
                    removeNode(list,currentNode);
                    return true;
                }
            } else {
                if(currentNode.getData().equals(data)) {
                    removeNode(list, currentNode);
                    return true;
                }
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

}
