public interface Series <T> {
     public String toString();
 
     /**
      * Returns the length of the series object.
      */
     public int getLength();
 
     /**
      * Returns the row names of this Series object.
      */
     public String[] getRowNames();
 
     /**
      * Returns the data of this Series object as strings.
      */
     public String[] getData();
 
     /**
      * Adds a new pair of rowName and data at the end of the Series object.
      *
      * @param rn the row name to be added
      * @param d the Integer data value to be added
      */
     public void append(String rn, T d);

     /**
      * Retrieves a data value given a row name.
      *
      * @param rn the row name to search for
      */
     public T loc(String rn);

     /**
      * Retrieves multiple data values given an array of row names.
      *
      * @param rn an array of row names to search for
      */
     public T[] loc(String[] rn);

     /**
      * Retrieves a data value based on its integer index.
      *
      * @param ind the index of the data to retrieve
      */
     public T iloc(int ind);

     /**
      * Removes a pair of rowname-data from the Series, given a row name.
      *
      * @param rn the row name of the pair to be removed
      */
     public boolean drop(String rn);

     /**
      * Replace any data value that is null with value.
      *
      * @param value the new value to replace null values
      */
     public void fillNull(T value);

     /**
      * Replace any data value that is null with the mean of the Series.
      *
      */
 }