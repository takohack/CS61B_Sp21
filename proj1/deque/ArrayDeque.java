package deque;

public class ArrayDeque<T> implements Deque<T>{
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
//        T item = null;
//        int current_first = nextFirst;
//        while(index >=0){
//            if(addOne(current_first) == nextLast){
//                return null;
//            }
//            current_first = addOne(current_first);
//            item = items[current_first];
//            index--;
//        }
//        return item;
        if(index >= size){
            return null;
        }
        int start = addOne(nextFirst);
        return items[(start + index) % items.length];
    }

}
