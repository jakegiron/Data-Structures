
/**
 * @author Jacobo Giron
 * 
 * A private class to be used in AVL tree
 */
class AVLNode{
	private int data; 	//the element value for this node
	private AVL parent;	//the parent node of this node
	private AVL left;	//the left child of this node
	private AVL right;	//the right child of this node
	private int height; //height of the tree rooted at this node 
		
	/**
	 * No-argument constructor
	 */
	public AVLNode(){
		parent = null;
		left = null;
		right = null;
		height = 0;
	}
	
	/**
	 * Constructor with the initial element
	 * @param initData: the initial element
	 */
	public AVLNode(int initData){
		data = initData;
		parent = null; 
		left = null;
		right = null;
		height = 0;
	}
	
	/**
	 * Constructor with the initial element, initial left and right children
	 * @param initData: the initial element
	 * @param initLeft: left child
	 * @param initRight: right child
	 */
	public AVLNode(int initData, AVL initLeft, AVL initRight){
		data = initData;
		parent = null; 
		left = initLeft;
		right = initRight;
		height = 1;
	}

	/**
	 * Evaluate whether this node is a leaf node or not
	 * @return true if it is a leaf node; otherwise, return false.
	 */
	public boolean isLeaf()
	{
		return ((left==null)&&(right==null));
	}
	
	/**
	 * Evaluate whether this node is a root node of a tree or not.
	 * @return true is it is a root node; otherwise, return false.
	 */
	public boolean isRoot()
	{
		return (parent==null);
	}
	
	
	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param set the element in this node
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * @return the left child
	 */
	public AVL getLeft() {
		return left;
	}

	/**
	 * @param the left child to be set
	 */
	public void setLeft(AVL left) {
		this.left = left;
	}

	/**
	 * @return the right child
	 */
	public AVL getRight() {
		return right;
	}

	/**
	 * @param the right child to be set
	 */
	public void setRight(AVL right) {
		this.right = right;
	}
	
	/**
	 * @return return the parent
	 */
	public AVL getParent() {
		return parent;
	}

	/**
	 * @param the parent node to be set
	 */
	public void setParent(AVL parent) {
		this.parent = parent;
	}
	
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the tree rooted at this node
	 */
	public void setHeight()
	{
		this.height = 1+Math.max(getLeftHeight(), getRightHeight());
	}
	
	
	/**
	 * Convert this BTNode to a string
	 */
	public String toString()
	{
		String str="";
		
		if(left==null) str +="p(null)-> ";
		else str +="p("+parent.getRoot().getData()+")-> ";
		
		if(left==null) str +="(null)";
		else str +="("+left.getRoot().getData()+")";
		
		str += data;
		
		if(right==null) str +="(null)";
		else str +="("+right.getRoot().getData()+")";
		
		return str;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	//Rebalancing related methods
	
	/**
	 * Get the height of the left subtree
	 */
	public int getLeftHeight(){
		if(left==null||left.getRoot()==null) 	return 0;
		else return left.getRoot().getHeight();
	}
	
	/**
	 * Get the height of the right subtree
	 * @return: the height of the right sub-tree
	 */
	public int getRightHeight(){
		if(right==null||right.getRoot()==null) 	return 0;
		else return right.getRoot().getHeight();
	}
	
}

public class AVL {
	
	private AVLNode	root; //instance variable to denote the root of the AVL tree
	
	//Constructors for the AVL tree
	public AVL()		{root = null;}
	public AVL(int e)	{root = new AVLNode(e,new AVL(),new AVL());}
	
	//Basic set the get methods
	public AVLNode getRoot() 			{return root;}
	public void setRoot(AVLNode root) 	{this.root = root;}
	public boolean isEmpty()			{return (root==null);}
	
	
	/**
	 * Check whether the tree (rooted at this node) is right heavy or not
	 * @return
	 */
	private boolean rightHeavy(){
		return (root.getLeftHeight() < root.getRightHeight());
	}
	
	/**
	 * Check whether the tree (rooted at this node) is left heavy or not
	 * @param node
	 * @return
	 */
	private boolean leftHeavy(){
		return (root.getLeftHeight() > root.getRightHeight());
	}
	
	/**
	 * Check whether the tree (rooted at this node) is right too heavy or not
	 * @return
	 */
	private boolean rightTooHeavy(){
		return ((root.getLeftHeight()+1)< root.getRightHeight());
	}
	
	/**
	 * Check whether the tree (rooted at this node) is left too heavy or not
	 * @param node
	 * @return
	 */
	private boolean leftTooHeavy(){
		return (root.getLeftHeight() > (root.getRightHeight()+1));
	}
	
	private AVL getLeftSubtree(){
		return root.getLeft();
	}
	
	private AVL getRightSubtree(){
		return root.getRight();
	}
	
	private void setHeight(){
		root.setHeight();
	}

	/**
	 * Re-balance the AVL tree when it is needed
	 */
	private void Rebalance()
	{
		root.setHeight(); //recalculate the height of the tree
		//System.out.println("root data="+root.getData()+",height="+root.getHeight());
		//System.out.println("root left tree height="+root.getLeftHeight());
		//System.out.println("root left tree height="+root.getRightHeight());
		
		//Balance the right sub-tree
		if(rightTooHeavy()){ //cases 1,3
			System.out.println("right too heavy");
			if(getRightSubtree().rightHeavy()){//case 1, right-too-heavy + right heavy; rotate to left: single rotation
				System.out.println("case 1\nbefore balance");inOrderPrt();System.out.println("");
				rotateLeft();
			}else{//case 3, right-too-heavy + left heavy; double rotation
				System.out.println("case 3\nbefore balance");inOrderPrt();System.out.println("");
				getRightSubtree().rotateRight(); 
				rotateLeft();				
			}
			System.out.println("after balance");inOrderPrt();System.out.println("\n-------------");
		}
		
		//Balance the left sub-tree
		if(leftTooHeavy()){ //cases 2, 4
			System.out.println("left too heavy");
			//case 2:left-too-heavy + left-heavy: single rotation
			if(getLeftSubtree().leftHeavy()){
				System.out.println("case 2\nbefore balance");inOrderPrt();System.out.println("");
				rotateRight();
			}
			//case 4: left-too-heavy + right-heavy: double rotation
			else{
				System.out.println("case 4\nbefore balance");inOrderPrt();System.out.println("");
				getLeftSubtree().rotateLeft();
				rotateRight();
			}
			System.out.println("after balance");inOrderPrt();System.out.println("\n-------------");
		}
	}
	
	
	/**
	 * 1) Let the left child of the current root be the new root
	 * 2) Push the current root to the right child of new root
	 */
	private void rotateRight() {
	     AVL temp = new AVL();
	     temp.setRoot(getLeftSubtree().getRoot()); //Replace the subtree rooted at z with a new subtree rooted at b
	     
	     //getLeft().setRoot(getLeft().getRight().getRoot());
	     AVL T2 = getLeftSubtree().getRightSubtree();
	     getLeftSubtree().setRoot(T2.getRoot()); //Set c’s left child be T2
	     
   	     //temp.getRight().setRoot(this.getRoot());
	     AVLNode c = this.getRoot();
	     temp.getRightSubtree().setRoot(c); //Set c be the right child of b
	     
	     setRoot(temp.getRoot()); 	//Set b be the new root
	     getRightSubtree().setHeight();	//Reset the height of the right subtree
	     setHeight();				//Reset the height of this subtree?
	}

	/**
	 * 1) Let the right child of the current root be the new root 
	 * 2) Push the current root to the left child of the current root's right left
	 */
	private void rotateLeft() {
	     AVL b = new AVL();
	     b.setRoot(getRightSubtree().getRoot()); //Set b be new root 
	     getRightSubtree().setRoot(getRightSubtree().getLeftSubtree().getRoot());//a's right child be T1
	     b.getLeftSubtree().setRoot(this.getRoot()); //set a be the left child of b
	     setRoot(b.getRoot()); //set b be the new root
	     
	     getLeftSubtree().setHeight(); //update the height of the left subtree
	     setHeight();	//update the height of this subtree
	}
	
	
	/**
	 * Insert e to the current AVL tree
	 * @param e
	 */
	public void Insert(int e)
	{
		if(isEmpty()){
			root = new AVLNode(e,new AVL(),new AVL());
		}else if(e<=root.getData()){
			getLeftSubtree().Insert(e);
			Rebalance();
		}else{
			getRightSubtree().Insert(e);
			Rebalance();
		}
	}
	
	
	/**
	 * Remove a specified element from the AVL tree. 
	 * When e exists in the tree and is successfully removed, return true; 
	 * Otherwise, return false.
	 * @param e
	 */
	public void Remove(int e)
	{
		if(root==null) return;
		if(e==root.getData()){
			if(getLeftSubtree().isEmpty()&&getRightSubtree().isEmpty()) //leaf
				root = null;
			else if(getLeftSubtree().isEmpty()&&!getRightSubtree().isEmpty())//has only left child
				root = getRightSubtree().getRoot();
			else if(!getLeftSubtree().isEmpty()&&getRightSubtree().isEmpty())//has only right child
				root = getLeftSubtree().getRoot(); 
			else{ //has both children
				root.setData(getLeftSubtree().removeMax());
				Rebalance();
			}
		}else if(e<root.getData()){
			getLeftSubtree().Remove(e);
			Rebalance();
		}else if(e>root.getData()){
			getRightSubtree().Remove(e);
			Rebalance();
		}	
	}
	
	/**
	 * Remove the current node from this AVL tree
	 * @param node
	 */
	private int removeMax(){
		int maxData=0;
		if(getRightSubtree().isEmpty()){
			maxData = root.getData();
			root = getLeftSubtree().getRoot();
		}else{
			maxData = getRightSubtree().removeMax();
			Rebalance();
		}
		return maxData;
	}
	
	
	/**
	 * Traversal the tree in-order and print it
	 */
	public void inOrderPrt(){
		inOrderPrt(0);
	}
	
	/**
	 * Private function to print the tree with in-order traversal
	 * @param indentation: the number of space before the data, to make the printing more beautiful
	 */
	private void inOrderPrt(int indentation){
		if(root!=null){
			if(root.getRight()!=null)root.getRight().inOrderPrt(indentation+1);
			for(int i=0;i<indentation*2;i++)
				System.out.print(" ");
			System.out.println(root.getData());
			if(root.getLeft()!=null)root.getLeft().inOrderPrt(indentation+1);
		}
	}
	
	
	
	/**
	 * Test functions
	 * @param args
	 */
	public static void main(String[] args) {
		testInsCase1DelCase3();
		testInsCase2DelCase2();
		testInsCase3DelCase3();
		testInsCase4DelCase4();
		testInsertOther();
	}
	
	private static void testInsCase1DelCase3(){
		System.out.println("\n********************** Test insertion case 1...");
		AVL avltree = new AVL();
		avltree.inOrderPrt();System.out.println();
		
		//normal insertion
		avltree.Insert(44); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(17); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(78); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(50); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(88); avltree.inOrderPrt();System.out.println("\n-------------\n");
		//insertion of test case 1
		avltree.Insert(80); avltree.inOrderPrt();System.out.println("\n-------------\n");
		
		System.out.println("Remove 44 (no need for balance)");
		avltree.Remove(44); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Remove 17 (no need for balance)");
		avltree.Remove(17);avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Remove 50 (case 3 (right too heavy + child left heavy)");
		avltree.Remove(50);avltree.inOrderPrt();System.out.println("\n-------------\n");
	}
	
	private static void testInsCase2DelCase2(){
		System.out.println("\n********************** Test insertion case 2...");
		AVL avltree = new AVL();
		avltree.inOrderPrt();System.out.println();
		
		//normal insertion
		avltree.Insert(44); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(17); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(78); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(15); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(32); avltree.inOrderPrt();System.out.println("\n-------------\n");
		//insertion of test case 2
		avltree.Insert(14); avltree.inOrderPrt();System.out.println("\n-------------\n");
		
		System.out.println("Remove 78 (no need for balance)");
		avltree.Remove(78); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Remove 32 (no need for balance)");
		avltree.Remove(32);avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Remove 44 (case 2 (left too heavy + child left heavy)");
		avltree.Remove(44);avltree.inOrderPrt();System.out.println("\n-------------\n");
	}
	
	private static void testInsCase3DelCase3(){
		System.out.println("\n********************** Test insertion case 3...");
		AVL avltree = new AVL();
		avltree.inOrderPrt();System.out.println();
		
		//normal insertion
		avltree.Insert(44); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(17); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(78); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(50); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(88); avltree.inOrderPrt();System.out.println("\n-------------\n");
		
		//insertion of test case 3
		avltree.Insert(15); avltree.inOrderPrt();System.out.println("\n-------------\n");
		
		System.out.println("testInsCase3DelCase1-Remove 17 (no need for balance)");
		avltree.Remove(17); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("testInsCase3DelCase1-Remove 50 (no need for balance)");
		avltree.Remove(50); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("testInsCase3DelCase1-Remove 15 (case 1: right too heavy + child right heavy)");
		avltree.Remove(15);avltree.inOrderPrt();System.out.println("\n-------------\n");
	}
	
	private static void testInsCase4DelCase4(){
		System.out.println("\n********************** Test insertion case 4...");
		AVL avltree = new AVL();
		avltree.inOrderPrt();System.out.println();
		
		//normal insertion
		avltree.Insert(44); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(17); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(78); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(15); avltree.inOrderPrt();System.out.println("\n-------------\n");
		avltree.Insert(32); avltree.inOrderPrt();System.out.println("\n-------------\n");
		//insertion of test case 4
		avltree.Insert(18); avltree.inOrderPrt();System.out.println("\n-------------\n");
		
		System.out.println("testInsCase4DelCase4-Remove 78 (no need for balance)");
		avltree.Remove(78); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("testInsCase4DelCase4-Remove 15 (no need for balance)");
		avltree.Remove(15); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("testInsCase4DelCase4-Remove 44 (case 4: left too heavy + child right heavy)");
		avltree.Remove(44);avltree.inOrderPrt();System.out.println("\n-------------\n");
	}
	
	private static void testInsertOther(){
		System.out.println("\n********************** Test insertion other cases...");
		AVL avltree = new AVL();
		avltree.inOrderPrt();System.out.println();
		
		System.out.println("Insert 44:");avltree.Insert(44); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 17:");avltree.Insert(17); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 78:");avltree.Insert(78); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 15:");avltree.Insert(15); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 32:");avltree.Insert(32); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 32:");avltree.Insert(18); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 33:");avltree.Insert(33); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 48:");avltree.Insert(48); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 62:");avltree.Insert(62); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 92:");avltree.Insert(92); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 45:");avltree.Insert(45); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 49:");avltree.Insert(49); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 54:");avltree.Insert(54); avltree.inOrderPrt();System.out.println("\n-------------\n");
		System.out.println("Insert 70:");avltree.Insert(70); avltree.inOrderPrt();System.out.println("\n-------------\n");
		//invoke complicated case 3
		System.out.println("Insert 53:");avltree.Insert(53); avltree.inOrderPrt();System.out.println("\n-------------\n");
		
	}
}