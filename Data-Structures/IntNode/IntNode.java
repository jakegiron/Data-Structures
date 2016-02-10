
/**
 * 
 * @author Jacobo Giron
 */
public class IntNode {

	private int data = 0;			
	private IntNode link = null; 

	/**
	 * No argument constructor
	 * @postcondition
	 * 	initialize data to be -1 and link to be null reference
	 */
	public IntNode(){
		data = -1;
	}
	
	/**
	 * A constructor with the desired node value and the link.
	 * @param _data: given node value
	 * @param _link: given node link
	 */
	public IntNode(int _data, IntNode _link){
		data = _data;
		link = _link;
	}
	
	/**
	 * Get method to get the node value.
	 * @return: node value
	 */
	public int getData() {
		return data;
	}

	/**
	 * Get method to get the node link.
	 * @return: node link
	 */
	public IntNode getLink() {
		return link;
	}

	/**
	 * Set method to set the node value.
	 * @param data: the given node value
	 */
	public void setData(int data) {
		this.data = data;
	}


	/**
	 * Set method to set the node link.
	 * @param link: the given link that this node should point to
	 */
	public void setLink(IntNode link) {
		this.link = link;
	}

	/**
	 * A method to add a node after the current node.
	 * @param newdata: the value put to the new node
	 */
	public void addNodeAfterThis(int newdata)
	{
		link = new IntNode(newdata,link);
	}
	
	/**
	 * A method to remove the node after the current node.
	 * @postcondition
	 * 	This method should remove the node that this node's link points to.
	 */
	public void removeNodeAfterThis()
	{
		link = link.link;
	}
	
	/**
	 * A method to get the number of nodes in the list starting from a given node.
	 * @param head
	 * @return
	 */
	public static int size(IntNode head)
	{
		int size=0;
		for(IntNode current = head; current!=null; current = current.getLink()){
			size++;
		}
		return size;
	}
	

	/**
	 * facilitating function
	 * Private method to help me construct the toString method
	 * @return: a string with ONLY this node's information
	 */
	private String nodeToString(){
		String str = ""+data;
		if(link!=null)
			str += "->";
		return str;
	}
	
	/**
	 * This method should return a String for the linked list 
	 * starting from the node that activates this method.
	 */
	public String toString(){
		String str="";
		for(IntNode current = this; current!=null; current = current.getLink()){
			str += current.nodeToString();
		}
		return str;
	}
	
	
	
	/**
	 * This method finds and returns the middle node of a linked list with the given head. Please note that the first node is at position 1. If the given list contains even number of nodes, 
	 *     The returned node should be the first node of the second half of the linked list.
	 *  
	 * @param head: the head node of a linked list
	 * @return: the node at the middle
	 */
	public static IntNode findMiddle (IntNode head)
	{
		if (head==null) return null; //special case process
		
		//   Calculate the middle position
		//   This step: the number of basic operations is 4 + (n*k1) where k1 is a constant
		int itemNum = size(head); //basic operations: n*k1 + 1 
		int pos = (itemNum%2==0)?((itemNum+1)/2):(itemNum/2); //basic operation 3
		
		//   Loop the list until we stop at the middle position
		//   In this loop, there are n/2 iterations, 
		//       each iteration, basic operation about 4 or 5 
		//   This step, the number of basic operation is 
		//       2 + (n/2 * k2) where k2 is about 4 or 5
		IntNode cursor = null;  //basic operation 1
		int index=0;				//basic operation 1
		for(cursor = head;cursor!=null; cursor=cursor.getLink()){
			if(index<pos) index++;
			else break;
		}
		
		return cursor; //basic operation 1
		
		//Overall, the number of basic operations is 
		//  4 + (n*k1) + 2 + (n/2 *k2) +1
		//  In Big O, the time complexity is O(n)
	}
	
	/**
	 *     This method removes nodes with duplicate elements in a linked list starting with the given head
	 *     I.e., it keeps only one node for every distinct element. 
	 * @param head: the head node of a linked list
	 * @postcondition: the list starting from the head does not contain any node with duplicated values 
	 */
	public static void removeDuplicate (IntNode head)
	{
		//   Initialization, basic operations: 3
		IntNode cursor1 = head;  
		IntNode cursor2 = null; 	//the cursor to remember the node that needs to be removed
		IntNode cursor2Pre = null;	//the cursor to remember the node before the node-to-be-removed
		
		//   For each element in the list (pointed by cursor1)
		//   There are "n" iterations where n is the number of nodes in the list
		//   In each iteration, the cursor2 will have "n-m" iterations, 
		//   		where m is the number of nodes before cursor1
		//   So, the number of operations for this loop is
		//   [3+(n-1)*k1] + [3+(n-2)*k1] + ... + 3+[1*k1] + 3 
		//   = 3*n + (n*(n-1)/2)*k1 
		//   where k1 is a constant number with the values 4 or 5 (happened in each iteration of the second loop)
		//         3 refers to the number of basic operations befor the second loop
		while(cursor1!=null){  
			cursor2Pre = cursor1;			//basic operation: 1
			cursor2 = cursor2Pre.getLink(); //basic operations: 2
			
			//check whether the list starting from cursor2
			//has any node with the same value as that of cursor1 
			while(cursor2!=null){
				if(cursor2.getData()!=cursor1.getData()){
					//if they are not equal, no need to remove, move forward cursor2
					cursor2Pre = cursor2;
					cursor2 = cursor2Pre.getLink();
				}else{
					//if equal, remove the node that cursor2 points 
					cursor2Pre.setLink(cursor2.getLink());
					cursor2 =cursor2Pre.getLink(); 
				}
			}
			
			//move forward cursor1
			cursor1 = cursor1.getLink();
		}
		
		//Overall cost
		// 3 + 3*n + (n*(n-1)/2)*k1, in Big O, the time complexity is O(n^2) 
	}
	
	
	/**
	 * This method reverses a linked list with the given head and returns the head of the new list.
	 * @param head: the head node of a linked list
	 * @return: the head of the new linked list, which reverses the given list
	 */
	public static IntNode reverse (IntNode head)
	{
		//1. Initialization, basic operations 3
		IntNode cursor = null; 	//the cursor to loop the list
		IntNode prev = null;	//the node before the cursor
		IntNode next = null;	//the node after the cursor
		
		//   Each iteration, basic operations about 8
		//   the number of iterations is n where n is the number of nodes in the list
		for(cursor = head; cursor!=null; ){
			next = cursor.getLink(); //remember the next node for use in the loop
			cursor.setLink(prev); //Change the link of the current cursor to its previous node
			prev = cursor; //Remember the current cursor
			cursor = next; //advance the cursor
		}
		
		//Finish loop, basic operations: 2
		head = prev;
		return head;
		
		//Overall, the time complexity is 
		// 3+(8*n)+2, in Big O, it is O(n)
	}
	
	
	/** 
	 * main function to test all the methods
	 * @param args
	 */
	public static void main(String[] args) {
		
		//1. test case: for no-argument constructor
		System.out.println("1. test case: for no-argument constructor");
		IntNode node1 = new IntNode();
		
		//2. toString test case 1
		System.out.println("2. toString test case 1");
		System.out.println("node1="+node1);
		
		//3. test case for setData and getData
		System.out.println("3. test case for setData and getData");
		node1.setData(200);
		System.out.println("node1.data="+node1.getData());
		
		//4. test case for constructor with argument
		System.out.println("4. test case for constructor with argument");
		IntNode node2 = new IntNode(100,node1);
		System.out.println("node2="+node2);
		
		//5. test case for setLink and toString (complicated cases)
		System.out.println("5. test case for setLink and toString (complicated cases)");
		IntNode node3 = new IntNode(300,null);
		node1.setLink(node3);
		System.out.println("node2="+node2);
		System.out.println("node1="+node1);
		System.out.println("node3="+node3);
		
		//6. test case for getLink
		System.out.println("6. test case for getLink");
		IntNode nodex = node1.getLink();
		System.out.println("nodex should equal to node1, nodex="+nodex);
		System.out.println("(nodex==node1):"+ (nodex==node1));
		
		//7. test case for size
		System.out.println("7. test case for size");
		System.out.println("Size of list starting from node1 ="+IntNode.size(node1));
		System.out.println("Size of list starting from node2 ="+IntNode.size(node2));
		System.out.println("Size of list starting from node3 ="+IntNode.size(node3));
		System.out.println("Size of an empty list ="+IntNode.size(null));
		
		////////////////
		//8. Test case for find middle, removeNodeAfterThis, addNodeAfterthis, toString method 
		System.out.println("\n8. Test case for find middle, removeNodeAfterThis, addNodeAfterthis, toString method");
		System.out.println("[case1] node1:" +node1);//linked list has 2 nodes
		IntNode midNode = IntNode.findMiddle(node1);
		System.out.println("[case1] Link starts from midNode:" +midNode);
		
		node1.removeNodeAfterThis();  
		System.out.println("[case2] node1:" +node1);//linked list has 1 node 
		midNode = IntNode.findMiddle(node1);
		System.out.println("[case2] Link starts from midNode:" +midNode);
		
		node1.addNodeAfterThis(2); //special case for addNodeAfterThis: node1's link is a null reference 
		node1.addNodeAfterThis(3); //normal case for addNodeAfterThis: node1's link is not a null reference
		System.out.println("[case3] node1:" +node1); //linked list has 3 nodes
		midNode = IntNode.findMiddle(node1);
		System.out.println("[case3] Link starts from midNode:" +midNode);
		
		node1.addNodeAfterThis(4);
		System.out.println("[case4] node1:" +node1); //linked list has 4 nodes
		midNode = IntNode.findMiddle(node1);
		System.out.println("[case4] Link starts from midNode:" +midNode);
		
		node1.addNodeAfterThis(5);
		System.out.println("[case5] node1:" +node1); //linked list has 5 nodes
		midNode = IntNode.findMiddle(node1);
		System.out.println("[case5] Link starts from midNode:" +midNode);
		for(int i=0;i<10;i++){
			node1.addNodeAfterThis(i+20);
		}
		System.out.println("[case6] node1:" +node1); //linked list has 15 nodes
		midNode = IntNode.findMiddle(node1);
		System.out.println("[case6] Link starts from midNode:" +midNode);
		
		midNode = IntNode.findMiddle(null);
		System.out.println("[case7] Link starts from null: " +midNode);
		////////////////
		
		
		////////////////
		//9. Test case for removeDuplicate
		System.out.println("\n9. Test case for removeDuplicate");
		IntNode nodeRemove = new IntNode(10,null);
		System.out.println("[case1] Before remove:\t" +nodeRemove); 
		IntNode.removeDuplicate(nodeRemove);
		System.out.println("[case1] After remove duplicates:" +nodeRemove);
		
		//special case, this link contains nodes with the same value
		nodeRemove.addNodeAfterThis(10);
		nodeRemove.addNodeAfterThis(10);
		System.out.println("[case2] Before remove:\t" +nodeRemove); 
		IntNode.removeDuplicate(nodeRemove);
		System.out.println("[case2] After remove duplicates:" +nodeRemove);
		
		//A normal case: the duplicate occurs 3 times
		nodeRemove.addNodeAfterThis(5);
		nodeRemove.addNodeAfterThis(6);
		nodeRemove.addNodeAfterThis(5);
		nodeRemove.addNodeAfterThis(5);
		System.out.println("[case3] Before remove:\t" +nodeRemove); 
		IntNode.removeDuplicate(nodeRemove);
		System.out.println("[case3] After remove duplicates:" +nodeRemove);
		
		//A more normal case: many nodes have duplicates many times
		nodeRemove.addNodeAfterThis(10);
		nodeRemove.addNodeAfterThis(12);
		nodeRemove.addNodeAfterThis(5);
		nodeRemove.addNodeAfterThis(6);
		nodeRemove.addNodeAfterThis(5);
		nodeRemove.addNodeAfterThis(5);
		nodeRemove.addNodeAfterThis(6);
		System.out.println("[case4] Before remove:\t" +nodeRemove); 
		IntNode.removeDuplicate(nodeRemove);
		System.out.println("[case4] After remove duplicates:" +nodeRemove);
		
		//special case, given head is null, it should not give me any error
		IntNode.removeDuplicate(null);
		//remove duplicate
		////////////////
		
		
		////////////////
		//9. Test case for reverse
		//Normal reverse case
		System.out.println("\n10. Test case for reverse");
		System.out.println("[case1] Before reverse:\t" +nodeRemove);
		IntNode reversedHead = IntNode.reverse(nodeRemove);
		System.out.println("[case1] After reverse:\t" +reversedHead);
		
		//Special case for reverse: head is null
		reversedHead = IntNode.reverse(null);
		System.out.println("[case2] Reverse null:\t" +reversedHead);
		
		//Special case for reverse: with just one node
		IntNode nodeReverse = new IntNode(20,null);
		System.out.println("[case3] Before reverse:\t" +nodeReverse);
		reversedHead = IntNode.reverse(nodeReverse);
		System.out.println("[case3] After reverse:\t" +reversedHead);
		
		//Normal test case for reverse: list has duplicate values
		nodeReverse.addNodeAfterThis(6);
		nodeReverse.addNodeAfterThis(7);
		nodeReverse.addNodeAfterThis(7);
		System.out.println("[case4] Before reverse:\t" +nodeReverse);
		reversedHead = IntNode.reverse(nodeReverse);
		System.out.println("[case4] After reverse:\t" +reversedHead);
		////////////////		
	}

}