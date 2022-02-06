package deque;

import org.w3c.dom.Node;

public class LinkedListDeque<T> implements Deque<T> {
    private class StuffNode{
        public T item;
        public StuffNode pre;
        public StuffNode next;

        public StuffNode(T i,StuffNode pre,StuffNode next){
            item = i;
            this.pre = pre;
            this.next = next;
        }
    }

    private  StuffNode sentinel;
    private int size;

    public  LinkedListDeque(){
        sentinel = new StuffNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x){
        size ++;
        sentinel.next = new StuffNode(x,sentinel,sentinel.next);
        if(sentinel.pre == sentinel)
        {
            sentinel.pre = sentinel.next;
        }
        sentinel.next.next.pre = sentinel.next;

    }
    @Override
    public void addLast(T x){
        size ++;
        StuffNode pre_temp = sentinel.pre;
        sentinel.pre = new StuffNode(x,pre_temp,sentinel);
        if(sentinel.next == sentinel){
            sentinel.next = sentinel.pre;
        }
        sentinel.pre.pre.next = sentinel.pre;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        StuffNode node = sentinel.next;
        if(node == sentinel){
            return;
        }
        while (node != sentinel){
            System.out.print(node.item+" ");
            node = node.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        size--;
        StuffNode node = sentinel.next;
        sentinel.next = node.next;
        node.next.pre = sentinel;
        return node.item;
    }
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        size--;
        StuffNode node = sentinel.pre;
        sentinel.pre = node.pre;
        node.pre.next = sentinel;
        return node.item;
    }
    @Override
    public T get(int index){
        if(index >= size){
            return null;
        }
        StuffNode item = sentinel;
        while(index>=0){
            item = item.next;
            index --;
        }
        return item.item;
    }

    private T get_helper(StuffNode node,int index){
        if(index ==0){
            return node.item;
        }
        return get_helper(node.next,index-1);
    }

    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return get_helper(sentinel.next,index);
    }


}
