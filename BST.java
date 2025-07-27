public class BST<I extends Comparable<I>, T>{
    private BSTNode root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }
    private String inOrderTraversal(BSTNode node) {
        String bstString = "";
        if (node == null) {
            return bstString;
        }
        bstString += inOrderTraversal(node.left);
        bstString += node.toString();
        bstString += inOrderTraversal(node.right);
        return bstString;
    }
    public String toString() {
        String bstString = "In-order Traversal of the BST ...\n==================\n";
        return bstString + this.inOrderTraversal(this.root);
    }
    public int getSize() {
        return this.size;
    }

    public void addNode(I _index, T _data) {
        if (this.root == null) {
            this.root = new BSTNode(_index, _data);
            this.size++;
        }
        addNodeHelper(this.root, _index, _data);
    }
    private void addNodeHelper(BSTNode currNode, I _index, T _data) {
        if (_index.compareTo(currNode.getIndex()) < 0 & currNode.left == null) {
            currNode.left = new BSTNode(_index, _data);
            this.size++;
        }
        else if (_index.compareTo(currNode.getIndex()) < 0) {
            addNodeHelper(currNode.left, _index, _data);
        }
        else if (_index.compareTo(currNode.getIndex()) > 0 & currNode.right == null) {
            currNode.right = new BSTNode(_index, _data);
            this.size++;
        }
        else if (_index.compareTo(currNode.getIndex()) > 0) {
            addNodeHelper(currNode.right, _index, _data);
        }
    }
    public BSTNode searchNode(I _index) {
        if (this.root == null) {
            return null;
        }
        return searchNodeHelper(this.root, _index);
    }
    private BSTNode searchNodeHelper (BSTNode currNode, I _index) {
        if (currNode == null) {
            return null;
        }
        if (_index.compareTo(currNode.getIndex()) < 0) {
            return searchNodeHelper(currNode.left, _index);
        }
        else if (_index.compareTo(currNode.getIndex()) > 0) {
            return searchNodeHelper(currNode.right, _index);
        }
        else if (_index.compareTo(currNode.getIndex()) == 0) {
            return currNode;
        }
        else {
            return null;
        }
    }
    public void removeNode(I _index) throws IllegalArgumentException{
        if (this.root == null) {
            throw new IllegalArgumentException("removeNode(I _index): No node with an index " + _index + " in the BST");
        }
        this.root = removeNodeHelper(this.root, _index);
        this.size--;
    }
    private BSTNode removeNodeHelper (BSTNode currNode, I _index) {
        if (currNode == null) {
            throw new IllegalArgumentException("removeNode(I _index): No node with an index " + _index + " in the BST");
        }
        if (_index.compareTo(currNode.getIndex()) < 0) {
            currNode.left = removeNodeHelper(currNode.left, _index);
        }
        else if (_index.compareTo(currNode.getIndex()) > 0) {
            currNode.right = removeNodeHelper(currNode.right, _index);
        }
        else {
            if (currNode.right == null & currNode.left == null) {
                return null;
            }
            else if (currNode.left == null) {
                return currNode.right;
            }
            else if (currNode.right == null) {
                return currNode.left;
            }
            else {
                I successorIndex = findMinIndex(currNode.right);
                T successorData = findMinData(currNode.right);
                currNode.index = successorIndex;
                currNode.data = successorData;
                currNode.right = removeNodeHelper(currNode.right, successorIndex);
            }
        }
        return currNode;
    }
    public I findMinIndex(BSTNode currNode) {
        I minvalue = currNode.index;
        while (currNode.left != null) {
            minvalue = currNode.left.index;
            currNode = currNode.left;
        }
        return minvalue;
    }
    public T findMinData(BSTNode currNode) {
        T minvalue = currNode.data;
        while (currNode.left != null) {
            minvalue = currNode.left.data;
            currNode = currNode.left;
        }
        return minvalue;
    }
    public void updateNode(I _index, T _newData) throws IllegalArgumentException{
        if (this.root == null) {
            throw new IllegalArgumentException("updateNode(I _index, T _newData): No node with an index " +
                    _index + " in the BST");
        }
        else {
            updateNodeHelper(this.root, _index, _newData);
        }
    }
    private void updateNodeHelper (BSTNode currNode, I _index, T _newData) {
        if (currNode == null) {
            throw new IllegalArgumentException("updateNode(I _index, T _newData): No node with an index " +
                    _index + " in the BST");
        }
        if (_index.compareTo(currNode.getIndex()) < 0) {
            updateNodeHelper(currNode.left, _index, _newData);
        }
        else if (_index.compareTo(currNode.getIndex()) > 0) {
            updateNodeHelper(currNode.right, _index, _newData);
        }
        else {
            currNode.setData(_newData);
        }
    }
    public class BSTNode {
        private I index;
        private T data;
        private BSTNode left;
        private BSTNode right;

        public BSTNode() {
            index = null;
            data = null;
            left = null;
            right = null;
        }
        public BSTNode(I _index, T _data) {
            this.data = _data;
            this.index = _index;
        }
        public I getIndex() {
            return this.index;
        }
        public T getData() {
            return this.data;
        }
        public void setData(T d) {
            data = d;
        }
        public String toString() {
            return "index:\t" + index + ",\t" + "data:\t" + data + "\n";
        }
    }
}
