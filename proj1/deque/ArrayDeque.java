package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }
    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        int i = 0;
        for(int j=0;j<size;j++){
            a[i++] = items[(nextFirst + 1 + j)%items.length];
        }
        nextFirst = a.length - 1;
        nextLast = i;
        items = a;
    }
    private int minusOne(int index){
        if(index == 0){
            return items.length - 1;
        }
        index --;
        return index;
    }
    private int addOne(int index){
        if(index == (items.length - 1)){
            return 0;
        }
        index ++;
        return index;
    }
    @Override
    public void addFirst(T item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size ++;
    }
    @Override
    public void addLast(T item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        if(isEmpty()){
            return;
        }
        while(addOne(nextFirst) != nextLast){
            System.out.print(items[addOne(nextFirst)]+" ");
            nextFirst = addOne(nextFirst);
        }
        System.out.println();
    }
    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        double usage_factor = size/items.length;
        if(usage_factor >=0.25 && items.length >=16){
            resize(items.length/2);
        }
        size--;
        T item = items[addOne(nextFirst)];
        nextFirst = addOne(nextFirst);
        items[nextFirst] = null;
        return item;
    }
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        double usage_factor = (double)size/items.length;
        if(usage_factor <=0.25 && items.length >=16){
            resize(items.length/2);
        }
        size--;
        T item = items[minusOne(nextLast)];
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        return item;
    }
    @Override
    public T get(int index){
        if(index >= size){
            return null;
        }
        int start = addOne(nextFirst);
        return items[(start + index) % items.length];
    }
    private class arrayDequeIterator implements Iterator<T>{
        private int position;

        public arrayDequeIterator(){
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            T returnItem = items[(nextFirst + 1 + position)%items.length];
            position +=1;
            return returnItem;
        }
    }
    public Iterator<T> iterator(){
        return new arrayDequeIterator();
    }
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }else if(!(o instanceof ArrayDeque)){
            return false;
        }else if(o == this){
            return true;
        }
        ArrayDeque<T> otherObject = (ArrayDeque<T>) o;
        if(otherObject.size() != this.size()){
            return false;
        }
        Iterator<T> other_iter = otherObject.iterator();
        Iterator<T> my_iter = this.iterator();
        while(my_iter.hasNext()){
            if(my_iter.next() != other_iter.next()){
                return false;
            }
        }
        return true;
    }



}
