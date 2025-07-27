public class LL <T> {
    private LLNode head;
    private LLNode tail;
    private int length;

    public LL() {
        LLNode n  = new LLNode();
        LLNode n1  = new LLNode();
        head = n;
        head.next = n1;
        tail = n1;
        length = 0;
    }

    public String toString() {
        String printList = "print the linked list ...\n==================\n" + "null\t: null\n";
        LLNode pointer = head.next;
        for (int i = 0; i < length; i++) {
            if (pointer.index != null) {
                printList += pointer.index + "\t: " + String.valueOf(pointer.data) + "\n";
                pointer = pointer.next;
            }
        }
        printList += "null\t: null\n";
        return printList;
    }

    public int getLength() {
        return length;
    }

    public String[] getDataArray() {
        String[] dataArray = new String[length];
        LLNode pointer = head.next;
        for (int i = 0; i < length; i++) {
            if (pointer.data == null) {
                dataArray[i] = null;
            }
            else {
                dataArray[i] = pointer.data.toString();
            }
            pointer = pointer.next;
        }
        return dataArray;
    }

    public String[] getIndexArray() {
        String[] indexArray = new String[length];
        LLNode pointer = head.next;
        for (int i = 0; i < length; i++) {
            indexArray[i] = pointer.index;
            pointer = pointer.next;
        }
        return indexArray;
    }

    public void appendNode(String _index, T _data) {
        LLNode pointer = head;
        LLNode newNode = new LLNode(_index, _data);
        for (int i = 0; i < length + 1; i++) {
            if (pointer.next == tail) {
                pointer.next = newNode;
                newNode.next = tail;
                if (newNode.index == null || newNode.index.equals("")) {
                    newNode.index = String.valueOf(length);
                }
            }
            pointer = pointer.next;
        }
        length++;
    }

    public LLNode searchNode(String _index) {
        LLNode pointer = head;
        LLNode foundNode = null;
        for (int i = 0; i < length; i++) {
            if (pointer.next.index.equals(_index)) {
                foundNode = pointer.next;
                break;
            }
                pointer = pointer.next;
        }
        return foundNode;
    }

    public void removeNode (String _index) throws IllegalArgumentException{
        LLNode pointer = head;
        int oldLength = length;
        for (int i = 0; i < length; i++) {
            if (pointer.next.index.equals(_index)) {
                pointer.next = pointer.next.next;
                length--;
                break;
            }
            pointer = pointer.next;
        }
        if (oldLength == length) {
            throw new IllegalArgumentException("removeNode(String _index): No " +
                    "node with an index " + _index + " in the list");
        }
    }

    public void updateNode(String _index, T value) throws IllegalArgumentException{
        LLNode pointer = head;
        int nodesParsed = 0;
        for (int i = 0; i < length; i++) {
            if (pointer.next.index.equals(_index)) {
                pointer.next.setData(value);
                break;
            }
            pointer = pointer.next;
            nodesParsed++;
        }
        if (nodesParsed == length) {
            throw new IllegalArgumentException("updateNode(String _index, T value): No node " +
                    "with an index " + _index + " in the list");
        }
    }

    public class LLNode {
        private String index;
        private T data;
        private LLNode next;

        public LLNode() {
            index = null;
            data = null;
            next = null;
        }

        public LLNode(String _index, T _data) {
            data = _data;
            index = _index;
        }

        public String getIndex() {
            return index;
        }

        public T getData() {
            return data;
        }

        public void setData(T d) {
            data = d;
        }
    }
}
