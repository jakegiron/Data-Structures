
/**
 * Created by jacob on 9/29/14.
 */
public class DoublyLinkedListDummy {
    private DIntNode head;
    private DIntNode tail;
    private int size = 0;

    /**setter and getter methods*/
    public DIntNode getHead() {return head;}
    public DIntNode getTail() {return tail;}
    public int getSize() {return size;} //used for debugging
    public void setHead(DIntNode head) {this.head = head;}
    public void setTail(DIntNode tail) {this.tail = tail;}

    /**
     * no arguments constructor
     *
     * creates dummy head and tail and links them
     */
    public DoublyLinkedListDummy(){
        head = new DIntNode();
        tail = new DIntNode();
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }//end no-argument constructor

    /**
     * addEnd method
     * adds node to the end of the list
     * @param element data being added
     */
    public void addEnd(int element){
        DIntNode nNode = new DIntNode(element, tail.getPrev(), tail);
        tail.getPrev().setNext(nNode);
        tail.setPrev(nNode);
        size++;
    }//end addEnd

    /**
     * removeFromLast Method
     * removes last element in the list
     *
     * O(4)
     */
    public void removeFromLast(){
        if (tail.getPrev() == head) return;

        tail.getPrev().getPrev().setNext(tail);
        tail.setPrev(tail.getPrev().getPrev());
        size--;
    }//end RemoveFromLast

    /**
     * toString method
     * returns a representation of the list forwards and backwards
     * @return listStr
     */
    public String toString(){
        String listStr = "(Forward)  ";
        DIntNode cursor = head.getNext();
        while(cursor != tail){
            if(cursor != head.getNext())
                listStr += "<->";
            listStr += cursor.getData();
            cursor = cursor.getNext();
        }//end while

        listStr +="\n(Backward) ";
        cursor = tail.getPrev();
        while(cursor != head){
            if(cursor != tail.getPrev())
                listStr += "<->";
            listStr += cursor.getData();
            cursor = cursor.getPrev();
        }//end while

        return listStr;
    }//end toString

    /**
     * countOccurrence
     * counts occurrences of given parameter
     * @param e data
     * @return count
     *
     * O(n)
     *      n being the list
     */
    public int countOccurrences(int e){
        int count = 0;
        DIntNode cursor = head.getNext();
        while (cursor != tail){
            if (cursor.getData() == e) count++;
            cursor = cursor.getNext();
        }
        return count;
    }

    /**
     * removeAll method
     * removes all node with value e
     * @param e integer value
     * @return boolean value
     *
     * O(n)
     *      n being the list
     */
    public boolean removeAll(int e){
        DIntNode cursor = head;
        int c = 0;
        boolean bool = false;
        while (cursor.getNext() != tail){
            if (cursor.getData() == e){
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                c++;
            }//end if
            cursor = cursor.getNext();
        }//end while
        if (c > 0) bool = true;
        return bool;
    }//end removeAll

    /**
     * subList method
     * finds sublist withing given indexes
     * @param beginIdx beginning index
     * @param endIdx end index
     * @return DIntNode
     *
     * O(n^2)
     *      n being the list
     */
    public DoublyLinkedListDummy subList(int beginIdx, int endIdx) {
        if (beginIdx < 0 || beginIdx > endIdx) return null;

        DoublyLinkedListDummy newList = new DoublyLinkedListDummy();
        DIntNode cursor = head.getNext();
        for (int i = 2; i <= beginIdx; i++) {
            cursor = cursor.getNext();
        }//end for

        DIntNode endCursor = head.getNext();
        for (int i = 2; i <= endIdx; i++) endCursor = endCursor.getNext();


        while (cursor != endCursor.getNext()){
            newList.addEnd(cursor.getData());
            cursor = cursor.getNext();
        }//end while


        return newList;
    }//end subList



//    public void printStatistics(){
////        DIntNode cursor = head.getNext();
////        int count;
////        int[] array = new int[size];
////        int i = 0;
////
////        while (cursor != tail){
////            array[i] = cursor.getData();
////            i++;
////            cursor = cursor.getNext();
////        }
////
//////        for (i = 0; i < array.length; i++){
//////            System.out.println(array[i]);
//////        }
////        for (i = 0; i < size; i++) {
////            int e = array[i];
////            count = 0;
////            size = array.length;
////            for (i = 0; i < size; i++){
////                if (e == array[i]){
////                    count++;
////                    array[i] = array[size];
////                }
////            }
////            size--;
////            System.out.println(e + "\t" + count);
////        }
//
//
//        System.out.println("*******************8");
//        DIntNode cursor = head.getNext();
//        int count;
//        int array[][] = new int[size][2];
//        int i = 0;
//
//        while (cursor != tail){
//            for (int j = 0; j < i; i++) {
//                if (cursor.getData() == array[j][1])
//                    continue;
//                else
//            }
//            i++;
//
//            cursor = cursor.getNext();
//        }
//
//        for (i = 0; i < size;i++)
//            for (int j =0; j < 2; j++)
//                System.out.println(array[i][j]);
//
//    }//end printStatistics

}//end class















































