/**
 *@author jacob giron
 */

public class ComputerCollection {

    private Computer[] data = null;
    private int manyItems;

    /**
     * ComputerCollection
     *
     * no arguments constructor
     */
    public ComputerCollection(){
        data = new Computer[10];
        manyItems = 0;
    }//end ComputerCollection

    /**
     * ComputerCollection
     *
     * @param capacity
     *              initial capacity of array
     * @throws Exception
     *              parameter must be positive
     */
    public ComputerCollection(int capacity) throws Exception{
        if (capacity <= 0){
            throw new Exception(capacity + " Parameter must be a positive number");
        }//end if
        data = new Computer[capacity];
        manyItems = 0;
    }//end ComputerCollection

    /**
     * ComputerCollection
     *
     * @param o
     *              object being copied
     * @throws Exception
     *              Parameter cannot be null
     *              Parameter must be an instance of ComputerCollection
     */
    public ComputerCollection(Object o) throws Exception{
        if (o == null) throw new Exception("Parameter cannot be null");
        if (!(o instanceof ComputerCollection)) throw new Exception("Parameter must be an instance of ComputerCollection");

        ComputerCollection tmp = (ComputerCollection) o;

        data = new Computer[tmp.getCapacity()];
        manyItems = tmp.getSize();

        for (int i = 0; i < manyItems; i++) data[i] = tmp.data[i];
    }//end ComputerCollection

    /**
     * add
     *
     * @param comp
     *              Computer being added to array
     */
    public void add(Computer comp) throws Exception{
        if (comp == null) throw new Exception("Parameter cannot be null");
        ensureCapacity(manyItems);
        data[manyItems] = comp;
        manyItems++;
    }

    /**
     * getSize
     *
     * @return
     *              Items in the array
     */
    public int getSize(){
        return manyItems;
    }

    /**
     * getCapacity
     *
     * @return
     *              returns capacity of the array
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * getData
     *      This was to debug
     * @return
     *              returns data for toString method to work
     */
    public Computer[] getData(){
        return data;
    }

    /**
     * contains
     *
     * @param a
     *              checks for this parameter
     * @return
     *              true or false
     */
    public boolean contains(Computer a){
        if (a == null) return false;

        for (int i = 0; i < data.length; i++){
            if (a == data[i]) return true;
        }//end if
        return false;
    }//end contains

    /**
     * remove
     *
     * @param a
     *              removes this parameter if in the array
     * @return
     *              true or false
     */
    public boolean remove(Computer a){
        if (!(contains(a))) return false;

        for (int i = 0; i < manyItems; i++){
            if (a == data[i]){
                data[i] = data[manyItems-1];
                data[manyItems-1] = null;
                manyItems--;
            }//end if
        }//end for
        return true;
    }//end remove

    /**
     * ensureCapacity
     *
     * @param minimumCapacity
     *              this is equal to manyItems when passed
     */
    private void ensureCapacity(int minimumCapacity){
        Computer[] bigCollection;
        if (minimumCapacity >= data.length) {
            bigCollection = new Computer[data.length * 2];
            for (int i = 0; i < this.data.length; i++) bigCollection[i] = data[i];
            data = bigCollection;
        }
    }//end ensureCapacity
}//end main
