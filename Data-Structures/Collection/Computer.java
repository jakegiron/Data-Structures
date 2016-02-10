
/**
 *@author jacob giron
 */

public class Computer {
    private String maker;
    private String CPU;
    private int memory;
    private int disk;
    private String serialNo;

    /**
     * no argument constructor
     *
     * creates computer object
     */
    public Computer(){
        maker = "" ;
        CPU = "" ;
        memory = 0;
        disk = 0;
        serialNo = "" ;
    }//end Computer

    /**
     *
     * @param _maker
     * @param _CPU
     * @param _memory
     * @param _disk
     * @param _serialNo
     */
    public Computer(String _maker, String _CPU, int _memory, int _disk, String _serialNo){
        maker = _maker;
        CPU = _CPU;
        memory = _memory;
        disk = _disk;
        serialNo = _serialNo;
    }

    /**
     * computer construct
     *
     * @param _maker make of the computer
     * @param _serialNo serial number of the computer
     *
     * creates computer object and assigns maker & serialNo
     */
    public Computer(String _maker, String _serialNo){
        maker = _maker;
        CPU = "";
        memory = 0;
        disk = 0;
        serialNo = _serialNo;
    }//end Computer

    /**
     * computer copy constructor
     *
     * @param obj the object intended to copy
     *
     * @throws java.lang.IllegalArgumentException object cannot be null and must be an
     * instance of Computer
     */
    public Computer(Object obj) throws IllegalArgumentException {
        if (obj == null) throw new IllegalArgumentException("Object is null");

        if (obj instanceof Computer){
            maker = ((Computer) obj).maker;
            CPU = ((Computer) obj).CPU;
            disk = ((Computer) obj).disk;
            memory = ((Computer) obj).memory;
            serialNo = ((Computer) obj).serialNo;
        }else {
            throw new IllegalArgumentException("Object is not an instance of Coputer");
        }//end if
    }//end Computer

    /**
     *getMaker
     */
    public String getMaker() {
        return maker;
    }//end getMaker

    /**
     *getCPU
     */
    public String getCPU() {
        return CPU;
    }//end getCPU

    /**
     *getMemory
     */
    public int getMemory() {
        return memory;
    }//end getMemory

    /**
     *setDisk
     */
    public void setDisk(int disk) {
        this.disk = disk;
    }

    /**
     *setCPU
     */
    public void setCPU(String CPU) {
        this.CPU = CPU;
    }//end setCPU

    /**
     *setMemory
     */
    public void setMemory(int memory) {
        this.memory = memory;
    }//end setMemory

    /**
     *equals method
     *
     * @param obj object cannot be null and must be an instance of computer
     *
     * determines whether two Computer objects are equal
     */
    public boolean equals(Object obj){
        if (obj != null) {
            if (obj instanceof Computer){
                if (((Computer) obj).maker.equals(maker)
                        && ((Computer) obj).CPU.equals(CPU)
                        && ((Computer) obj).disk == disk
                        && ((Computer) obj).memory == memory
                        && ((Computer) obj).serialNo.equals(serialNo)) return true;
            }//end if
        }//end if
        return false;
    }//end equals

    /**
     * distanceBetween
     *
     * @param c1 Computer object
     * @param a2 Computer object
     *
     *
     * @throws java.lang.IllegalArgumentException
     *
     * @return double
     *
     * Determines the total size of two disks
     */
    public static double distanceBetween(Computer c1, Computer a2) throws IllegalArgumentException{
        if (c1 == null || a2 == null) throw new IllegalArgumentException("c1 nor a2 cannot be null");
        return (double) (a2.disk + c1.disk);
    }//end distanceBetween

    /**
     * toString Method
     *
     * @return String
     */
    public String toString(){
        return "Maker " + maker + " | " + "CPU " + CPU + " | " + "Memory Size " + memory
                + " gigs | " + "Disk Size: " + disk + " gigs | " + "Serial Number" + serialNo;
    }//end toString
}//end class

