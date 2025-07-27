public class SeriesV2<T> implements Series<T> {
    private LL<T> seriesData;
    private BST<String, T> seriesDataBST;
    /**
     * Constructs a new Series object.
     *
     * @param _rowNames an array of row names
     * @param _data an array of Integer data
     */
    public SeriesV2(String[] _rowNames, T[] _data) {
        seriesData = new LL<>();
        seriesDataBST = new BST<>();
        if (_data == null) {
            throw new NullPointerException("Series(String[] _index, T[] _data): " +
                    "_data can't be null. Terminating the program");
        }
        try {
            if (_data.length != _rowNames.length) {
                throw new IllegalArgumentException("Series(String[] _index, T[] _data): " +
                        "the length of _index and _data must be the same");
            }
            for (int i = 0; i < _rowNames.length; i++) {
                if (_rowNames[i] == null) {
                    throw new IllegalArgumentException("Series(String[] _index, T[] _data): " +
                            "_rowNames is not valid");
                }
            }
            for (int i = 0; i < _rowNames.length; i++) {
                seriesData.appendNode(_rowNames[i], _data[i]);
                seriesDataBST.addNode(_rowNames[i], _data[i]);
            }
        }
        catch (NullPointerException e) {
            for (int i = 0; i < _data.length; i++) {
                seriesData.appendNode(Integer.toString(i), _data[i]);
                seriesDataBST.addNode(Integer.toString(i), _data[i]);
            }
        }
    }

    /**
     * Returns a string representation of the Series object.
     */
    public String toString() {
        String newString = seriesData.toString();
        return newString.replace("linked list", "series");
    }

    /**
     * Returns the length of the series object.
     */
    public int getLength() {
        return seriesData.getLength();
    }

    /**
     * Returns the row names of this Series object.
     */
    public String[] getRowNames() {
        return seriesData.getIndexArray().clone();
    }

    /**
     * Returns the data of this Series object as strings.
     */
    public String[] getData() {
        return seriesData.getDataArray().clone();
    }

    /**
     * Adds a new pair of rowName and data at the end of the Series object.
     *
     * @param rn the row name to be added
     * @param d the Integer data value to be added
     */
    public void append(String rn, T d) {
        if (rn == null) {
            seriesDataBST.addNode(Integer.toString(seriesDataBST.getSize()), d);
            seriesData.appendNode(Integer.toString(seriesData.getLength()), d);
        }
        else {
            seriesData.appendNode(rn, d);
            seriesDataBST.addNode(rn, d);
        }
    }

    /**
     * Retrieves a data value given a row name.
     *
     * @param rn the row name to search for
     */
    public T loc(String rn) throws NullPointerException, IllegalArgumentException {
        if (rn == null) {
            throw new NullPointerException("loc(String rn): rn can't be null");
        }
        if (rn.isEmpty()) {
            throw new IllegalArgumentException("loc(String rn): rn can't be an empty string");
        }
        try {
            return seriesDataBST.searchNode(rn).getData();
        } catch (NullPointerException e){
            return null;
        }
    }

    /**
     * Retrieves multiple data values given an array of row names.
     *
     * @param rn an array of row names to search for
     */
    public T[] loc(String[] rn) throws NullPointerException, IllegalArgumentException {
        if (rn == null) {
            throw new NullPointerException("loc(String[] rn): rn[] can't be null");
        }
        if (rn.length == 0) {
            throw new IllegalArgumentException("loc(String[] rn): rn[] can't be an empty array");
        }
        T[] rnArray = (T[]) new Object[rn.length];
        for (int i = 0; i < rn.length; i++) {
            try {
                rnArray[i] = this.loc(rn[i]);
            } catch (NullPointerException e) {
                rnArray[i] = null;
            }
        }
        return rnArray;
    }

    /**
     * Retrieves a data value based on its integer index.
     *
     * @param ind the index of the data to retrieve
     */
    public T iloc(int ind) {
        try {
            String LLdata = seriesData.getIndexArray()[ind];
            return seriesDataBST.searchNode(LLdata).getData();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("the index " + ind + " is not valid.. returning null");
            return null;
        }
    }

    /**
     * Removes a pair of rowname-data from the Series, given a row name.
     *
     * @param rn the row name of the pair to be removed
     */
    public boolean drop(String rn) throws NullPointerException, IllegalArgumentException {
        if (rn == null) {
            throw new NullPointerException("drop(String ind): ind can't be null");
        }
        if (rn.isEmpty()) {
            throw new IllegalArgumentException("drop(String ind): ind can't be an empty String");
        }
        int indexrn = -1;
        for (int i = 0; i < seriesData.getLength(); i++) {
            if (seriesData.getIndexArray()[i].equals(rn)) {
                indexrn = i;
                break;
            }
        }
        if (indexrn == -1) {
            return false;
        }
        else {
            seriesData.removeNode(rn);
            seriesDataBST.removeNode(rn);
            return true;
        }
    }

    /**
     * Replace any data value that is null with value.
     *
     * @param value the new value to replace null values
     */
    public void fillNull(T value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException("fillNull(T value): value can't be null");
        }
        for (int i = 0; i < seriesData.getLength(); i++) {
            if (seriesData.getDataArray()[i] == null) {
                seriesData.updateNode(seriesData.getIndexArray()[i], value);
                seriesDataBST.updateNode(seriesData.getIndexArray()[i], value);
            }
        }
    }

    /**
     * Replace any data value that is null with the mean of the Series.
     *
     */
}
