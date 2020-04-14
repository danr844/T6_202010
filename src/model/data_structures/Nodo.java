package model.data_structures;


public class Nodo<Key, Value>
{
     Key key;           // key
     Value val;         // associated data
     Nodo<Key,Value> left, right;  // links to left and right subtrees
     boolean color;     // color of parent link
     int size;          // subtree count

    public Nodo(Key pkey, Value pval, boolean pcolor, int psize) {
        this.key = pkey;
        this.val = pval;
        this.color = pcolor;
        this.size = psize;
    }
}



