package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;
    private int length;
    private int front;
    private int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4; //当前有元素的头
        last = 4;  //可以插入的最后一个位置
    }

    public int size() {
        return size;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index, int module) {
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }
    private void resize(int cap){
        T[] newArray = (T[]) new Object[cap];
        int ptr1 = front;
        int i = 0;
        while (ptr1 != last){
            newArray[i++] = array[ptr1];
            ptr1 = plusOne(ptr1,length);
        }
        front = 0;
        last = i;
        array = newArray;
        length = cap ;
    }





    public void addFirst(T item) {
        if(size == length -1 ){
            resize(length * 2);
        }
        front = minusOne(front);
        array[front] = item;
        size++;
    }

    public void addLast(T item) {
        if(size == length -1){
            resize(length * 2);
        }
        array[last] = item;
        last = plusOne(last,length);
        size++;
    }



    public void printDeque() {
        for(int i=0;i<size;i++){
            System.out.println(get(i)+ " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if(length >=16 && length/size >=4){
            resize(length/2);
        }
        if(size == 0){
            return null;
        }
        T ret = array[front];
        front = plusOne(front,length);
        size--;
        return ret;
    }

    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            resize(length/2);
        }
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        size--;
        return array[last];
    }



    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int ptr = (front + index)%length;
//        int ptr = front;
//        for(int i=0;i<index;i++){
//            ptr = plusOne(ptr,length);
//        }
        return array[ptr];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> ad = (ArrayDeque<?>) o;
        if (ad.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (ad.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T item = get(index);
            index += 1;
            return item;
        }
    }
}