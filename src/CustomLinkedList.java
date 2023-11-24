public class CustomLinkedList<T> implements GenericLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> previous;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;

        }
    }

    @Override
    public void add(T data) {
        Node<T>  newNode = new Node<>(data);
        if(head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        size++;
    }


    @Override
    public void remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        if(current.previous != null){
            current.previous.next = current.next;
        } else {
            head = current.next;
        }
        if(current.next == null) {
            tail = current.previous;
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Node<T> current = head;
        while(current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
