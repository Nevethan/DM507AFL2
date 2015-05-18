/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm507afl2;

import java.util.Arrays;

/**
 *
 * @author Eger
 */
public class PQHeap implements PQ {
    Element[] elements;
    int heapSize = 1;
    public PQHeap(int maxElements) {
        elements = new Element[maxElements+1];
    }

    @Override
    public Element extractMin() {
        Element element = elements[1];
        elements[1] = elements[heapSize-1];
        checkAndSwapChildren(1);
        heapSize--;
        return element;
    }

    private void checkAndSwapChildren(int i) {
        if(hasTwoChildren(i)&&(key(i) > key(left(i)) || key(i) > key(right(i)))){
            int minChild = minChild(i);
//            System.out.println(minChild);
            swap(i, minChild);
            checkAndSwapChildren(minChild);
        }else if(hasLeftChild(i)&&key(i) > key(left(i))){
            swap(i, left(i));
        }
    }

    private boolean hasTwoChildren(int i) {
        return hasLeftChild(i) && hasRightChild(i);
    }

    private boolean hasRightChild(int i) {
        return right(i)<heapSize;
    }

    private boolean hasLeftChild(int i) {
        return left(i)<heapSize;
    }

    private void swap(int a, int b) {
        Element p = elements[b];
        elements[b] = elements[a];
        elements[a] = p;
    }

    private int right(int i) {
        return 2*i+1;
    }

    private int left(int i) {
        return 2*i;
    }

    private int key(int i) {
        return elements[i].key;
    }

    private int minChild(int i) {
        return key(left(i)) < key(right(i)) ? left(i) : right(i);
    }

    @Override
    public void insert(Element e) {
        addElementToBottomOfHeap(e);
        checkAndSwapParent(heapSize - 1);
    }

    @Override
    public int getHeapSize() {
        return heapSize-1;
    }

    private void addElementToBottomOfHeap(Element e){
        elements[heapSize] = e;
        heapSize++;
    }
    private void checkAndSwapParent(int i) {
        if(i==1)return;
        if(key(i) < key(parent(i))){
            swap(i, parent(i));
            checkAndSwapParent(i / 2);
        }
    }

    private int parent(int i) {
        return i/2;
    }

    @Override
    public String toString() {
        return "PQHeap{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    public static void main(String[] args) {
        PQHeap pqHeap = new PQHeap(5);
        pqHeap.insert(new Element(5,null));
        pqHeap.insert(new Element(4,null));
        pqHeap.insert(new Element(7,null));
        System.out.println(pqHeap.extractMin());
        System.out.println(pqHeap.extractMin());
        pqHeap.insert(new Element(5, null));
    }
}
