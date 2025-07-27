public class HashTable<V> {
    private static final Object BRIDGE = new String("[BRIDGE]".toCharArray());
    private int size;
    private int capacity;
    private String[] keys;
    private V[] values;

    public HashTable() {
        this.size = 0;
        this.capacity = 4;
        this.keys = new String[this.capacity];
        this.values = (V[]) new Object[this.capacity];
    }
    public String toString() {
        String hashString = "printing the hash table ...\n==================\n";
        for (int i = 0; i < this.capacity; i++) {
            hashString += "index:\t" + i + ",\tkey:\t"  + this.keys[i] + ",\tdata:\t" + this.values[i] + "\n";
        }
        return hashString;
    }
    public int getSize() {
        return this.size;
    }
    public int getCapacity() {
        return this.capacity;
    }
    public String[] getKeyArray() {
        String[] keyArray = new String[this.size];
        int nullValue = 0;
        for (int i = 0; i < this.capacity; i++) {
            if (this.keys[i] == null) {
                nullValue++;
            }
            else {
                keyArray[i - nullValue] = this.keys[i];
            }
        }
        return keyArray;
    }
    public int getHashIndex(String k) {
        int hashValue = 0;
        for (int i = 0; i < k.length(); i++) {
            int letter = k.charAt(i) - 96;
            hashValue += (hashValue * 27 + letter);
        }
        return hashValue % this.getCapacity();
    }
    public V lookup(String k) throws NullPointerException {
        if (k == null) {
            throw new NullPointerException("lookup(String key): key is null");
        }
        V temp = null;
        for (int i = 0; i < this.capacity; i++) {
            if (this.keys[i] == null) {
                continue;
            }
            if (this.keys[i].equals(k)) {
                temp = this.values[i];
            }
        }
        return temp;
    }
    private void sizeUp() {
        this.capacity *= 2;
        String[] tempKeys = this.keys.clone();
        V[] tempValues = this.values.clone();
        this.keys = new String[this.capacity];
        this.values = (V[]) new Object[this.capacity];
        for (int i = 0; i < tempKeys.length; i++) {
            if (tempKeys[i] != null & tempKeys[i] != null) {
                this.size--;
                this.insert(tempKeys[i], tempValues[i]);
            }
        }
    }
    private void sizeDown() {
        this.capacity /= 2;
        if (this.capacity < 4) {
            this.capacity = 4;
        }
        String[] tempKeys = this.keys.clone();
        V[] tempValues = this.values.clone();
        this.keys = new String[this.capacity];
        this.values = (V[]) new Object[this.capacity];
        for (int i = 0; i < tempKeys.length; i++) {
            if (tempKeys[i] != null & tempValues[i] != null) {
                this.size--;
                this.insert(tempKeys[i], tempValues[i]);
            }
        }
    }
    public int insert(String k, V v) throws NullPointerException {
        if (k == null) {
            throw new NullPointerException("insert(String k, V v): k is null");
        }
        if (v == null) {
            throw new NullPointerException("insert(String k, V v): v is null");
        }
        int hashIndex = getHashIndex(k);
        if (this.keys[hashIndex] == null) {
            this.keys[hashIndex] = k;
            this.values[hashIndex] = v;
            this.size++;
        }
        else if (this.keys[hashIndex].equals(k)) {
            this.values[hashIndex] = v;
        }
        else {
            while (this.keys[hashIndex] != null) {
                hashIndex = (hashIndex + 1) % this.capacity;
            }
            this.keys[hashIndex] = k;
            this.values[hashIndex] = v;
            this.size++;
        }
        if ((double)(this.size)/(double)(this.capacity) >= 0.55) {
            this.sizeUp();
        }
        return hashIndex;
    }
    public int delete(String k) {
        int removedIndex = this.getHashIndex(k);
        for (int i = 0; i < this.capacity; i++) {
            if (this.keys[i] == null) {
                continue;
            }
            if (this.keys[i].equals(k)) {
                this.keys[i] = (String) BRIDGE;
                this.values[i] = null;
                this.size--;
                removedIndex = i;
            }
        }
        if ((double)(this.size)/(double)(this.capacity) <= 0.3) {
            this.sizeDown();
        }
        return removedIndex;
    }
}
