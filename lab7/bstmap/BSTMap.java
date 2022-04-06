package bstmap;


import java.util.Iterator;
import java.util.Set;

public class BSTMap<Key extends Comparable<Key>, Value>  implements Map61B<Key, Value> {
    private class BSTNode {
        private Key key;
        private Value val;
        private BSTNode left,right;
        private int size;

        public BSTNode(Key key,Value val,int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    private BSTNode root;

    public BSTMap() {

    }

    private int size(BSTNode x) {
        if (x==null) return 0;
        else return x.size;
    }
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(Key key) {
        if (key == null ) throw new IllegalArgumentException("argument to contains() is null");
        return check_key(root,key);
    }
    private Boolean check_key(BSTNode x,Key key){
        if(key == null) return false;
        if(x == null ) return  false;
        int cmp = key.compareTo(x.key);
        if (cmp<0) return check_key(x.left,key);
        else if (cmp > 0) return check_key(x.right,key);
        else return true;
    }

    private Value get(BSTNode x,Key key){
        if (key == null ) throw new IllegalArgumentException("calls get() with a null key");
        if( x == null ) return null;
        int cmp = key.compareTo(x.key);
        if (cmp<0) return get(x.left,key);
        else if (cmp > 0) return get(x.right,key);
        else return x.val;
    }

    @Override
    public Value get(Key key) {
        return get(root,key);
    }

    @Override
    public int size() {
        return size(root);
    }

    private BSTNode put(BSTNode x,Key key,Value val) {
        if(x == null ) return new BSTNode(key,val,1);
        int cmp = key.compareTo(x.key);
        if (cmp <0) x.left = put(x.left,key,val);
        else if (cmp > 0 ) x.right = put(x.right,key,val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    @Override
    public void put(Key key, Value value) {
        if(key == null ) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root,key,value);
    }

    @Override
    public Set<Key> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value remove(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value remove(Key key, Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Key> iterator() {
        throw new UnsupportedOperationException();
    }
}
