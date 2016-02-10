
/**
 * Created by jacob on 9/29/14.
 */
public class TestCases {
    public static void main(String[] args) {

        System.out.println("******TEST CASES FOR LAB 6********");
        System.out.println("testing no argument constructor");
        DoublyLinkedListDummy newList = new DoublyLinkedListDummy();
        System.out.println(newList.toString());

        System.out.println("\ntesting addEnd method");

        newList.addEnd(3);
        System.out.println(newList.toString());
        newList.addEnd(6);
        System.out.println(newList.toString());
        newList.addEnd(5);
        System.out.println(newList.toString());
        newList.addEnd(6);
        System.out.println(newList.toString());
        newList.addEnd(3);
        System.out.println(newList.toString());
        newList.addEnd(6);
        System.out.println(newList.toString());


        System.out.println("\ntesting removeFromLast method");
        newList.removeFromLast();
        System.out.println(newList.toString());
        newList.removeFromLast();
        System.out.println(newList.toString());

        newList.addEnd(3);
        newList.addEnd(6);

        System.out.println("\ntesting countOccurrence method");
        System.out.println("using this list: \n" + newList);
        System.out.println("occurrences of 3: " + newList.countOccurrences(3));
        System.out.println("occurrences of 6: " + newList.countOccurrences(6));
        System.out.println("occurrences of 1: " + newList.countOccurrences(1));

        System.out.println("\ntesting removeAll");
        boolean testRemAll;
        testRemAll = newList.removeAll(5);
        if (testRemAll) System.out.println("removing 5:\n" + newList);
        testRemAll = newList.removeAll(3);
        if (testRemAll) System.out.println("removing 3:\n" + newList);
        testRemAll = newList.removeAll(1);
        if (testRemAll) System.out.println("removing 1:\n" + newList);

        System.out.println("\ntesting subList method");
        DoublyLinkedListDummy newList1 = new DoublyLinkedListDummy();

        newList1.addEnd(1);
        newList1.addEnd(2);
        newList1.addEnd(4);
        newList1.addEnd(2);
        newList1.addEnd(6);
        newList1.addEnd(7);
        newList1.addEnd(2);
        newList1.addEnd(9);
        newList1.addEnd(7);
        newList1.addEnd(7);
        newList1.addEnd(12);
        newList1.addEnd(2);

        DoublyLinkedListDummy testSubList;
        testSubList = newList1.subList(3,8);
        System.out.println(testSubList.toString());

        testSubList = newList1.subList(-100, 1044);
        System.out.println(testSubList);

        testSubList = newList1.subList(8, 4);
        System.out.println(testSubList);

        System.out.println("***********testing get and set**************");
        DoublyLinkedListDummy dummy = new DoublyLinkedListDummy();
        DIntNode node = new DIntNode(123);
        dummy.setHead(node);
        dummy.setTail(node);
        System.out.println(dummy.getHead());
        System.out.println(dummy.getTail());
        System.out.println("\n*******bonus******** I Couldn't Figure It Out ='(");
        DoublyLinkedListDummy bonus = new DoublyLinkedListDummy();
        bonus.addEnd(1);
        bonus.addEnd(2);
        bonus.addEnd(4);
        bonus.addEnd(2);
        bonus.addEnd(6);
        bonus.addEnd(7);
        bonus.addEnd(2);
        bonus.addEnd(9);
        bonus.addEnd(7);
        bonus.addEnd(7);
        bonus.addEnd(12);
        bonus.addEnd(2);

        System.out.println(bonus);

//        bonus.printStatistics();
    }//end main
}//end class
