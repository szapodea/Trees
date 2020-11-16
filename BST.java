public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;

    // CONSTRUCTOR 
    public BST() {
        this.root = null;
        this.N = 0;
    }

    // PUBLIC METHODS 

    //
    // insert a new (key, val) into tree
    // or replace value of existing key
    //
    public void insert(Key key, Value val) {
        root = insertHelper(root, key, val);
    }

    public Node insertHelper(Node root, Key key, Value val) {
        if (root == null) {
            root = new Node(key, val);
            root.height = 0;
            N++;
            return root;
        }
        if (root.key.compareTo(key) == 0) {
            root.val = val;
        } else if (root.key.compareTo(key) > 0) {
            root.left = insertHelper(root.left, key, val);
            if (root.left != null && root.height == root.left.height) {
                root.height++;
            }
        } else {
            root.right = insertHelper(root.right, key, val);
            if (root.right != null && root.height == root.right.height) {
                root.height++;
            }
        }
        return root;
    }

    //
    // get the value associated with the given key;
    // return null if key doesn't exist
    //
    public Value get(Key key) {
        return getHelper(root, key);
    }

    public Value getHelper(Node root, Key key) {
        if (root == null) {
            return null;
        }
        if (root.key.compareTo(key) == 0) {
            return root.val;
        } else if (root.key.compareTo(key) > 0) {
            return getHelper(root.left, key);
        } else {
            return getHelper(root.right, key);
        }
    }

    //
    // return true if the tree
    // is empty and false 
    // otherwise
    //
    public boolean isEmpty() {
        return root == null;
    }

    //
    // return the number of Nodes
    // in the tree
    //
    public int size() {
        return N;
    }

    //
    // returns the height of the tree
    //
    public int height() {
        return height(root);
    }

    //
    // returns the height of node 
    // with given key in the tree;
    // return -1 if the key does
    // not exist in the tree
    //
    public int height(Key key) {
        return heightHelper(root, key);
    }

    public int heightHelper(Node root, Key key) {
        if (root == null) {
            return -1;
        }
        if (root.key.compareTo(key) == 0) {
            return root.height;
        } else if (root.key.compareTo(key) > 0) {
            return heightHelper(root.left, key);
        } else {
            return heightHelper(root.right, key);
        }
    }
    //
    // return a String version of the tree
    // level by level
    //
    public String toString() {
        String str = "";
        Pair x = null;
        Queue<Pair> queue = new Queue<Pair>(N);
        int level = 0;
        queue.enqueue(new Pair(root, level));
        str += "Level 0: ";

        while (!queue.isEmpty()) {
            x = queue.dequeue();
            Node n = x.node;

            if (x.depth > level) {
                level++;
                str += "\nLevel " + level + ": ";
            }

            if (n != null) {
                str += "|" + n.toString() + "|";
                queue.enqueue(new Pair(n.left, x.depth + 1));
                queue.enqueue(new Pair(n.right, x.depth + 1));
            } else
                str += "|null|";
        }

        str += "\n";
        return str;
    }


    // PRIVATE METHODS 

    //
    // return the height of x
    // or -1 if x is null
    //
    private int height(Node x) {
        if (x == null)
            return -1;

        return x.height;
    }

    // NODE CLASS 
    public class Node {
        Key key;
        Value val;
        Node left, right;
        int height;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public String toString() {
            return "(" + key + ", " + val + "): " + height;
        }
    }

    // PAIR CLASS 
    public class Pair {
        Node node;
        int depth;

        public Pair(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

}
