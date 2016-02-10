/**
 *@author jacob giron
 */

public class TestCases {

    public static void main(String[] args) throws Exception {
        //creating Computer objects for testing
        Computer compA = new Computer("Asus", "i7", 16, 512, "serial1");
        Computer compB = new Computer("Dell", "i3", 8, 1024, "serial2");
        Computer compC = new Computer("Asus", "i7", 1, 256, "serial3");
        Computer compD = new Computer("HP", "amd", 2, 64, "serial4");
        Computer compE = new Computer("Acer", "i5", 4, 32, "serial5");
        Computer compTest = new Computer("red", "amd", 32, 128, "serial0");

        boolean bool; //This is to test remove method

        System.out.println("\ntesting no argument constructor(collection1), add method, and get methods");
        ComputerCollection collection1 = new ComputerCollection();
        System.out.println("collection1 size: " + collection1.getSize() + "\t\tcollection1 capacity: " + collection1.getCapacity());
        collection1.add(compA);
        collection1.add(compB);
        collection1.add(compC);
        collection1.add(compD);
        collection1.add(compE);
        collection1.add(compB);
        collection1.add(compE);
        collection1.add(compA);
        System.out.println("after adding 8 Computer objects");
        System.out.println("collection1 size: " + collection1.getSize() + "\t\tcollection1 capacity: " + collection1.getCapacity());

        System.out.println("Collection before remove:");
        for (int i = 0; i < collection1.getSize(); i++) System.out.println(collection1.getData()[i].toString());

        System.out.println("\ntesting remove & contains with 3 different parameters then printing with for loop");
        bool = collection1.remove(compTest);
        System.out.println(bool ? "Computer was removed" : "Computer does not exist");

        bool = collection1.remove(compB);
        System.out.println(bool ? "Computer was removed" : "Computer does not exist");

        bool = collection1.remove(null);
        System.out.println(bool ? "Computer was removed" : "Computer does not exist");

        System.out.println("collection1 size: " + collection1.getSize() + "\tcollection1 capacity: " + collection1.getCapacity());

        System.out.println("Collection after remove:");
        for (int i = 0; i < collection1.getSize(); i++) System.out.println(collection1.getData()[i].toString());

        System.out.println("\n**********************************\n");

        System.out.println("testing constructor with capacity parameter (collection2)");
        ComputerCollection collection2 = new ComputerCollection(5);
        System.out.println("collection2 size: " + collection2.getSize() + "\t\tcollection2 capacity: " + collection2.getCapacity());

        System.out.println("\ntesting add and ensureCapacity");

        collection2.add(compA);
        collection2.add(compA);
        collection2.add(compA);
        collection2.add(compC);
        collection2.add(compA);
        collection2.add(compA);
        System.out.println("capacity of collection2 after adding 6 objects: " + collection2.getCapacity());

        System.out.println("\nprinting all objects in collection using a for loop:");
        for (int i = 0; i < collection2.getSize(); i++) System.out.println(collection2.getData()[i].toString());
        System.out.println("collection2 size: " + collection2.getSize() + "\t\tcollection2 capacity: " + collection2.getCapacity());

        System.out.println("\n**********************************\n");

        System.out.println("Testing copy constructor");
        System.out.println("making a copy of collection2");

        ComputerCollection collectionCopy = new ComputerCollection(collection2);

        System.out.println("\nprinting all objects in collection using a for loop");
        System.out.println("collection2 size: " + collection2.getSize() + "\t\tcollection2 capacity: " + collection2.getCapacity());
        for (int i = 0; i < collection2.getSize(); i++) System.out.println(collection2.getData()[i].toString());
        System.out.println("");
        System.out.println("collectionCopy size: " + collectionCopy.getSize() + "\t\tcollectionCopy capacity: " + collectionCopy.getCapacity());
        for (int i = 0; i < collectionCopy.getSize(); i++) System.out.println(collectionCopy.getData()[i].toString());

        System.out.println("\n**********************************\n");

        System.out.println("Testing errors\n");
        ComputerCollection collectionErr =  null;
        Object fakeCollection = new Object();
        Computer computerErr = null;

        System.out.println("testing errors in capacity constructor");
        try {
            collectionErr = new ComputerCollection(0);
        }catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
        }

        try {
            collectionErr = new ComputerCollection(-7);
        }catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
        }

        System.out.println("\ntesting errors in copy constructor");

        try {
            collectionErr = new ComputerCollection(collectionErr);
        }catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
        }

        try {
            collectionErr = new ComputerCollection(fakeCollection);
        }catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
        }

        System.out.println("\ntesting errors in add method");

        try {
            collection2.add(computerErr);
        }catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
        }

        System.out.println("");
    }//end main
}//end class
