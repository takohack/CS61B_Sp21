package deque;

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (Item[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }
    private void resize(int capacity){
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items,0,a,0,size);
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

    public void addFirst(Item item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size ++;
    }
    public void addLast(Item item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
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
    public Item removeFirst(){
        if(isEmpty()){
            return null;
        }
        size--;
        Item item = items[addOne(nextFirst)];
        nextFirst = addOne(nextFirst);
        items[nextFirst] = null;
        return item;
    }

    public Item removeLast(){
        if(isEmpty()){
            return null;
        }
        size--;
        Item item = items[minusOne(nextLast)];
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        return item;
    }

    public Item get(int index){
        Item item = null;
        int current_first = nextFirst;
        while(index >=0){
            if(addOne(current_first) == nextLast){
                return null;
            }
            current_first = addOne(current_first);
            item = items[current_first];
            index--;
        }
        return item;
    }

}
