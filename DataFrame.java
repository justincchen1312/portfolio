public class DataFrame {
    private HashTable<SeriesV2<Object>> tabularData;
    private int numRows;
    private int numCols;

    public DataFrame() {
        tabularData = new HashTable<>();
        numRows = 0;
        numCols = 0;
    }
    public DataFrame(String _k, SeriesV2<Object> _series) {
        tabularData = new HashTable<>();
        numRows = _series.getLength();
        numCols = 1;
        tabularData.insert(_k, _series);
    }
    public SeriesV2<Object> colLoc(String k) {
        return tabularData.lookup(k);
    }
    public String toString(){
        String dfString = "printing the dataframe ...\n==================\n";
        int count = 0;
        for (int i = 0; i < tabularData.getKeyArray().length; i++) {
                dfString += "[colName:\t" + this.getColNames()[i] + "]\n";
                dfString += tabularData.lookup(this.getColNames()[i]).toString() + "\n";
        }
        return dfString;
    }
    public int getNumRows() {
        return this.numRows;
    }
    public int getNumCols() {
        return this.numCols;
    }
    public String[] getColNames() {
        return tabularData.getKeyArray().clone();
    }
    public void addColumn(String k, SeriesV2<Object> s) throws IllegalArgumentException {
        if (s.getLength() != this.numRows) {
            throw new IllegalArgumentException("addColumn(String k, SeriesV2<Object> s): " +
                    "the length of s does not match the dataframe's # of rows");
        }
        tabularData.insert(k, s);
        numCols++;
    }
    public void removeColumn(String k) {
        for (int i = 0; i < tabularData.getKeyArray().length; i++) {
            if (tabularData.getKeyArray()[i].equals(k)) {
                tabularData.delete(k);
                numCols--;
            }
        }
    }
}
