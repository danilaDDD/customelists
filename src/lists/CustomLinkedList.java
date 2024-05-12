package lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomLinkedList<E> implements CustomList<E>, CustomDeque<E>{
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;

    @Override
    public void addFirst(E element) {
        if(size == 0){
          addToEmptyList(element);
        }
        else{
            Node<E> newNode = new Node<>(null, firstNode, element);
            Node<E> nextNode = this.firstNode;
            this.firstNode = newNode;
            nextNode.firstNode = newNode;
        }

        size++;
    }

    @Override
    public void addLast(E element) {
        if(size == 0){
            addToEmptyList(element);
        } else{
            Node<E> newNode = new Node<>(lastNode, null, element);
            Node<E> prevNode = this.lastNode;
            this.lastNode = newNode;
            prevNode.lastNode = newNode;

        }
        size ++;
    }

    @Override
    public E getFirst() {
        return firstNode != null ? firstNode.element: null;
    }

    @Override
    public E getLast() {
        return lastNode != null ? lastNode.element: null;
    }

    @Override
    public E removeFirst() {
        return removeFirstNode().element;
    }

    @Override
    public E removeLast() {
       return removeLastNode().element;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public E remove(int index) {
        return removeNode(index).element;
    }

    @Override
    public E remove(E element) {
        return removeNode(element).element;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    @Override
    public void set(int index, E element) {
        Node<E> node = getNode(index);
        node.element = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = firstNode;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                Node<E> result = new Node<>(node);
                node = node.lastNode;

                return result.element;
            }
        };
    }

    @Override
    public String toString() {
        List<E> elements = new ArrayList<>(size);
        for (E element: this) {
            elements.add(element);
        }
        return elements.toString();
    }

    private Node<E> removeNode(E element){
        NodeAndIndex<E> nodeAndIndex = getNodeByElement(element);
        Node<E> foundNode = nodeAndIndex.node;
        int index = nodeAndIndex.index;

        if(foundNode != null){
            if(index == 0)
                return removeFirstNode();
            else if (index == size - 1) {
                return removeLastNode();
            } else if (index < size) {
                return removeMiddleNode(foundNode);
            }
        }

        throw new NoSuchElementException();
    }

    private NodeAndIndex<E> getNodeByElement(E element){
        Node<E> node = this.firstNode;
        Node<E> targetNode = null;
        int targetIndex = -1;

        for (int i = 0; i < size; i++) {
            if(node.element.equals(element)) {
                targetNode = node;
                targetIndex = i;
                break;
            }

            node = node.lastNode;
        }

        return new NodeAndIndex<>(targetIndex, targetNode);
    }

    private Node<E> removeFirstNode(){
        ifEmptyThrowException();
        Node<E> deletedNode = new Node<>(this.firstNode);

        Node<E> nextNode = this.firstNode.lastNode;
        nextNode.firstNode = null;
        this.firstNode = nextNode;

        size --;
        return deletedNode;
    }

    private Node<E> removeLastNode(){
        ifEmptyThrowException();
        Node<E> deletedNode = new Node<>(this.lastNode);

        Node<E> prevNode = this.lastNode.firstNode;
        this.lastNode.firstNode = null;
        prevNode.lastNode = null;
        this.lastNode = prevNode;

        size --;
        return deletedNode;
    }

    /*
    * The method removes the node after  the first node and after the last node
     */
    private Node<E> removeMiddleNode(Node<E> node){
        Node<E> deletedNode = new Node<>(node);

        Node<E> prevNode = node.firstNode;
        Node<E> nextNode = node.lastNode;
        prevNode.lastNode = nextNode;
        nextNode.firstNode = prevNode;
        node.lastNode = null;
        node.firstNode = null;

        size --;
        return deletedNode;
    }

    private Node<E> removeNode(int index){
        if(index == 0)
            return removeFirstNode();
        else if (index == size - 1) {
            return removeLastNode();
        } else if (index < size) {
            return removeMiddleNode(getNode(index));
        }

        throw new IndexOutOfBoundsException();
    }

    private void ifEmptyThrowException(){
        if(size == 0)
            throw new IndexOutOfBoundsException();
    }
    
    private Node<E> getNode(int index){
        Node<E> node = this.firstNode;
        for (int i = 0; i < index; i++) {
            node = node.lastNode;
        }

        return node;
    }

    private void addToEmptyList(E element){
        Node<E> node = new Node<>(null, null, element);
        firstNode = node;
        lastNode = node;
    }

    private static class Node<E>{
        public E element;
        public Node<E> firstNode;
        public Node<E> lastNode;

        public Node(Node<E> firstNode, Node<E> lastNode, E element) {
            this.lastNode = lastNode;
            this.firstNode = firstNode;
            this.element = element;
        }

        public Node(Node<E> node){
            firstNode = node.firstNode;
            lastNode = node.lastNode;
            element = node.element;
        }
    }

    private static class NodeAndIndex<E>{
        public Node<E> node;
        public int index;

        public NodeAndIndex(int index, Node<E> node) {
            this.index = index;
            this.node = node;
        }
    }
}
