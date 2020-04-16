package model.data_structures;


public class Nodo<Key, Value>
{
     public Key key;           // key
     public Value val;         // associated data
     public Nodo<Key,Value> left, right;  // links to left and right subtrees
     public boolean color;     // color of parent link
     public int size;          // subtree count

    public Nodo(Key pkey, Value pval, boolean pcolor, int psize) {
        this.key = pkey;
        this.val = pval;
        this.color = pcolor;
        this.size = psize;
    }
}



