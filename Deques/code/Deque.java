import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() {
        Node first = null;
        Node last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        Node newFirst = new Node(item);
        if (isEmpty()) {
            first = newFirst;
            last = newFirst;
        }
        else {
            newFirst.next = first;
            first.prev = newFirst;
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Item cannot be null");
        Node newLast = new Node(item);
        if (isEmpty()) {
            first = newLast;
            last = newLast;
        }
        else {
            last.next = newLast;
            newLast.prev = last;
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item item = first.item;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item item = last.item;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.removeLast();
        deque.removeLast();
        System.out.println(deque.isEmpty());
    }
}