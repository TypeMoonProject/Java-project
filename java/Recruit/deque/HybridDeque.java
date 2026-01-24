package deque;
import java.util.NoSuchElementException;

/**
 * @author @厌。 (QQ:669518272)
 * @version 1.0
 */

public class HybridDeque<E> {

    // ===== 双向链表节点 =====
    private static class Node<E> {
        E val;
        Node<E> prev, next;
        Node(E v) { this.val = v; }
    }

    // ===== 前半段：双向链表（带哨兵）=====
    private final Node<E> head = new Node<>(null); // sentinel
    private final Node<E> tail = new Node<>(null); // sentinel
    private int frontSize = 0;

    // ===== 后半段：数组（已实现，你只需调用）=====
    private final Object[] arr;
    private int backSize = 0;

    public HybridDeque(int arrayCapacity) {
        if (arrayCapacity <= 0) throw new IllegalArgumentException();
        this.arr = new Object[arrayCapacity];
        head.next = tail;
        tail.prev = head;
    }

    public int size() { return frontSize + backSize; }
    public boolean isEmpty() { return size() == 0; }

    // ========= 需要你实现：对外操作 =========

    public void addFirst(E x) {
        // TODO: 插到链表头，然后 rebalance
        listAddFirst(x);
        rebalance();
    }

    public void addLast(E x) {
        // TODO: 插到数组尾，然后 rebalance
        arrayAddLast(x);
        rebalance();
    }

    public E removeFirst() {
        // TODO: 空抛异常；优先删链表头，否则删数组头；rebalance
        E re=null;
        if(isEmpty()){
            throw new NoSuchElementException("empty");
        }else if(frontSize != 0) {
            re = listRemoveFirst();
        }else {
            re = arrayRemoveFirst();
        }
        rebalance();
        return re;
    }

    public E removeLast() {
        // TODO: 空抛异常；优先删数组尾，否则删链表尾；rebalance
        E re=null;
        if(isEmpty()){
            throw new NoSuchElementException("empty");
        }else if(backSize != 0) {
            re = arrayRemoveLast();
        }else {
            re = listRemoveLast();
        }
        rebalance();
        return re;
    }

    public E peekFirst() {
        // TODO: 空返回 null；优先看链表头，否则看数组头
        E re = null;
        if(frontSize != 0) {
            re = head.next.val;
        }else if(backSize != 0) {
            re = arrayGet(0);
        }
        return re;
    }

    public E peekLast() {
        // TODO: 空返回 null；优先看数组尾，否则看链表尾
        E re = null;
        if(backSize != 0) {
            re = arrayGet(backSize-1);
        }else if(backSize != 0) {
            re = tail.prev.val;
        }
        return re;
    }

    // ========= 需要你实现：rebalance =========
    private void rebalance() {
        // TODO: 保证 |frontSize - backSize| <= 1
        // frontSize > backSize + 1: move one from list tail -> array head
        // backSize > frontSize + 1: move one from array head -> list tail
        if(frontSize > backSize + 1) {
            arrayAddFirst(listRemoveLast());
        }else if(backSize > frontSize + 1) {
            listAddLast(arrayRemoveFirst());
        }
    }

    // ========= 需要你实现：链表 4 个基本操作 =========
    private void listAddFirst(E x) {
        final Node<E> temp = new Node<>(x);
        temp.next = head.next;
        temp.prev = head.prev;
        head.next.prev = temp;
        head.next = temp;
        frontSize++;
    }
    private void listAddLast(E x) {
        final Node<E> temp = new Node<>(x);
        temp.next = tail;
        temp.prev = tail.prev;
        tail.prev.next = temp;
        tail.prev = temp;
        frontSize++;
    }
    private E listRemoveFirst() {
        E re = head.next.val;
        head.next.next.prev = head;
        head.next = head.next.next;
        frontSize--;
        return re;
    }
    private E listRemoveLast() {
        E re = tail.prev.val;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        frontSize--;
        return re;
    }

    // ========= 数组段操作：已实现（普通连续数组） =========
    private void arrayAddFirst(E x) {
        if (backSize == arr.length) throw new IllegalStateException("array part full");
        for (int i = backSize - 1; i >= 0; i--) arr[i + 1] = arr[i];
        arr[0] = x;
        backSize++;
    }

    private void arrayAddLast(E x) {
        if (backSize == arr.length) throw new IllegalStateException("array part full");
        arr[backSize++] = x;
    }

    @SuppressWarnings("unchecked")
    private E arrayRemoveFirst() {
        E v = (E) arr[0];
        for (int i = 1; i < backSize; i++) arr[i - 1] = arr[i];
        arr[backSize - 1] = null;
        backSize--;
        return v;
    }

    @SuppressWarnings("unchecked")
    private E arrayRemoveLast() {
        int idx = backSize - 1;
        E v = (E) arr[idx];
        arr[idx] = null;
        backSize--;
        return v;
    }

    @SuppressWarnings("unchecked")
    private E arrayGet(int i) {
        return (E) arr[i];
    }
}
