package model.data_structures;

import java.util.NoSuchElementException;

public class RedBlackBST < Key extends Comparable<Key>, Value>{


	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Nodo<Key, Value> root;
	private int size;
	
	private boolean isRed(Nodo<Key, Value> x)
	{
		if (x == null) 
			return false;
		return x.color == RED;
	}
	public Nodo<Key, Value> rotateLeft(Nodo<Key, Value> h)
	{
		Nodo<Key, Value> x = h.right; 
		h.right = x.left; 
		x.left = h; 
		x.color = h.color; 
		h.color = RED; 
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right); 
		return x;
	}
	public Nodo<Key, Value> rotateRight(Nodo<Key, Value> h)
	{
		Nodo<Key, Value> x = h.left; 
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}
	public void flipColors(Nodo<Key, Value> h)
	{
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK; 
	}
	public void put(Key key, Value val)
	{
		root= put(root, key, val);
		root.color= BLACK;
		size++;
	} 
	private Nodo<Key, Value> put(Nodo<Key, Value> h, Key key, Value val)
	{ 
		if(h == null) 
			return new Nodo<Key, Value>(key, val, RED, 1);
		int cmp= key.compareTo(h.key); 
		if(cmp< 0) 
			h.left= put(h.left, key, val);
		else if(cmp> 0) 
			h.right= put(h.right, key, val); 
		else
			h.val= val;
		if(isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h); 
		if(isRed(h.left) && isRed(h.right)) 
			flipColors(h); 
		h.size= size(h.left) + size(h.right) + 1;
		return h;
	} 
	public void delete(Key key) { 
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the given key rooted at h
	private Nodo<Key, Value> delete(Nodo<Key, Value> h, Key key) { 
		// assert get(h, key) != null;

		if (key.compareTo(h.key) < 0)  {
			if (!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}
		else {
			if (isRed(h.left))
				h = rotateRight(h);
			if (key.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				Nodo<Key, Value> x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				// h.val = get(h.right, min(h.right).key);
				// h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			}
			else h.right = delete(h.right, key);
		}
		return balance(h);
	}
	private Nodo<Key, Value> balance(Nodo<Key, Value> h) {
		// assert (h != null);

		if (isRed(h.right))   
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))  
			flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
	/**
	 * Removes the smallest key and associated value from the symbol table.
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the minimum key rooted at h
	private Nodo<Key, Value> deleteMin(Nodo<Key, Value> h) { 
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}

	private Nodo<Key, Value> moveRedLeft(Nodo<Key, Value> h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) { 
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private Nodo<Key, Value> moveRedRight(Nodo<Key, Value> h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.left.left)) { 
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return min(root).key;
	}
	public Value getMin(){
		return min(root).val;
	}
	// the smallest key in subtree rooted at x; null if no such key
	private Nodo<Key, Value> min(Nodo<Key, Value> x) { 
		// assert x != null;
		if (x.left == null) return x; 
		else                return min(x.left); 
	} 

	
	public Value getMaxValue(){
		return max(root).val;
	}
	private Nodo<Key, Value> max(Nodo<Key, Value> x) { 
		// assert x != null;
		if (x.right == null) 
			return x; 
		else    
			return min(x.right); 
	} 
	public Key maxKey() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    } 

    // the largest key in the subtree rooted at x; null if no such key



	/**
	 * Returns the value associated with the given key.
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *     and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	// value associated with the given key in subtree rooted at x; null if no such key
	private Value get(Nodo<Key, Value> x, Key key) {
		while (x != null)
		{
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else         
				return x.val;
		}
		return null;
	}
	public int height() {
        return height(root);
    }
	public int heightNode(Nodo<Key, Value> h){
		return height(h);
	}
	public Nodo<Key, Value> giveRoot()
	{
		return root;
	}
    private int height(Nodo<Key, Value> x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

	public boolean contains(Key key) {
        return get(key) != null;
    }
	public boolean isEmpty() {return false;}
	public int size(Nodo<Key, Value> h)
	{
		if(h==null) 
			return 0;
		return h.size;
	}
	/**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), maxKey());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the symbol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return queue;
    } 
    public double heightProm(){
    	return heightProm(root)/size;
    	
    }
    private double heightProm(Nodo<Key,Value>h){
    	if(h==null) return 0;
    	else
    	return 1+heightProm(h.left)+heightProm(h.right);
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Nodo<Key, Value> x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    } 
}
