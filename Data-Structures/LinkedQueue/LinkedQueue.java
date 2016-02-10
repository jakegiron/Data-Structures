
/**
 * Implementation of an FIFO queue using single linked list
 *  
 * @author Jacobo Giron
 * 
 */
public class LinkedQueue<E> implements MyQueue<E>{
	SNode<E> rear;				//the rear of a queue
	SNode<E> front;				//the front of a queue
	private int manyItems;		//the number of elements in the queue

	/**
	 * Create a list queue with empty rear and front nodes
	 */
	public LinkedQueue(){
		rear = null;
		front = null;
		manyItems = 0;
	}
	
	@Override
	public void enqueue(E e) {
		SNode<E> newNode = new SNode<E>(e,null);
		
		if(rear==null) { //empty queue
			front = rear = newNode;
		}else{ //add this new node be after rear
			rear.setNext(newNode);
			rear = newNode;
		}
		
		//update the number of elements
		manyItems++;
	}

	@Override
	public E dequeue() {
		if(front==null){ return null;} //empty queue
		else{ //non-empty queue
			
			//get the front element
			E answer = front.getElement();
			
			//move the front node
			front = front.getNext();
			
			//special case process
			if(front==null) rear = null;
			
			//update the number of elements
			manyItems--;
			return answer;
		}
	}

	@Override
	public E front() {
		if(front==null){ //empty queue
			return null;
		}else{//non-empty queue
			return front.getElement();
		}	
	}

	@Override
	public int size() {
		return manyItems;
	}

	@Override
	public boolean isEmpty() {
		return (manyItems==0);
	}

	/**
	 * Print the queue, for debugging purpose
	 */
	public void printQueue()
	{
		if(front==null){
			System.out.println("front="+front+",rear="+rear+",manyItems="+manyItems);
		}else{
			System.out.print("front="+front.getElement()+",rear="+rear.getElement()
					+",manyItems="+manyItems+": ");
			SNode<E> cursor = front;
			while(cursor!=null){
				System.out.print(cursor.getElement()+" ");
				cursor = cursor.getNext();
			}
			System.out.println();
		}
	}
	
	/**
	 * Test program
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
		System.out.println("queue size="+queue.size()+",is empty="+queue.isEmpty());
		
		queue.enqueue(10);

		System.out.println("queue size="+queue.size()+",queue front="+queue.front()
				+",queue size after enqueue="+queue.size());
		
		System.out.println("\nTest normal enqueue\n");
		for(int i=0;i<5;i++){
			queue.enqueue(i+1);
			queue.printQueue();
		}
		
		System.out.println("\nTest normal dequeue\n");
		while(!queue.isEmpty()){
			System.out.print("dequeue="+queue.dequeue()+" ");
			queue.printQueue();
		}
	}
}