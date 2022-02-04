package deque;

import org.w3c.dom.Node;

public class LinkedListDeque<NodeType> {
    private class StuffNode{
        public NodeType item;
        public StuffNode pre;
        public StuffNode next;

        public StuffNode(NodeType i,StuffNode pre,StuffNode next){
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


    public void addFirst(NodeType x){
        size ++;
        sentinel.next = new StuffNode(x,sentinel,sentinel.next);
        if(sentinel.pre == sentinel)
        {
            sentinel.pre = sentinel.next;
        }
        sentinel.next.next.pre = sentinel.next;

    }
    public void addLast(NodeType x){
        size ++;
        StuffNode pre_temp = sentinel.pre;
        sentinel.pre = new StuffNode(x,pre_temp,sentinel);
        if(sentinel.next == sentinel){
            sentinel.next = sentinel.pre;
        }
        sentinel.pre.pre.next = sentinel.pre;
    }

    public boolean isEmpty(){
        return size ==0;
    }

    public int size(){
        return size;
    }

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
    public NodeType removeFirst(){
        if(isEmpty()){
            return null;
        }
        size--;
        StuffNode node = sentinel.next;
        sentinel.next = node.next;
        node.next.pre = sentinel;
        return node.item;
    }
    public NodeType removeLast(){
        if(isEmpty()){
            return null;
        }
        size--;
        StuffNode node = sentinel.pre;
        sentinel.pre = node.pre;
        node.pre.next = sentinel;
        return node.item;
    }
    public NodeType get(int index){
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

    private NodeType get_helper(StuffNode node,int index){
        if(index ==0){
            return node.item;
        }
        return get_helper(node.next,index-1);
    }

    public NodeType getRecursive(int index){
        if(index >= size){
            return null;
        }
        return get_helper(sentinel.next,index);
    }


}
