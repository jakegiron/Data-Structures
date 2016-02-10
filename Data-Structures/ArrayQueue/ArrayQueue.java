
/**
 * ArrayQueue implementation
 * 
 * @author Jacobo Giron
 */
public class ArrayQueue<E> implements MyQueue<E>{
	//It has four instance variables. 
	private E[] data; 	//The first instance variable is an array holding all the elements in the queue.
	private int front; 	//The second instance variable is an integer denoting the front index of the queue.
	private int rear;	//The third instance variable is an integer denoting the rear index of the queue.
	private int manyItems; //The fourth instance variable denotes the number of elements in the queue.
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(){
		data = (E[]) new Object[10];
		manyItems = rear = front = 0;
	}

	//
	/**
	 * Add the element to the rear of the queue
	 * @postcondition: rear must be in [0,data.length)
	 */
	@Override
	public void enqueue(E e) {
		if(manyItems==data.length)
			ensureCapacity(manyItems*2+1);
		
		//put the element at the rear position
		//rear must be in the range of [0,data.length) 
		//because we perform the module operation every time we add an element
		data[rear] = e; 
		
		//update the value of rear;
		rear++;
		rear = (rear==data.length)?0:rear;
		
		//update the number of elements
		manyItems++;
	}

	/**
	 * Dequeue an element
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) return null;
		
		//front must be in the range of [0,data.length) 
		//because we perform the module operation every time we dequeue an element
		E answer= data[front];
		
		//update front pointer
		front = (front+1)%data.length;
		
		//update the number of elements
		manyItems--;
		return answer;
	}

	/**
	 * Get the front element in the queue
	 */
	@Override
	public E front() {
		if(isEmpty()) return null;
		return data[front];
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
	 * Ensure that data has "cap" space
	 * 
	 * @param cap
	 */
	private void ensureCapacity(int cap){
		E[] biggerData = null;
		
		if(data.length<cap){
			biggerData = (E[])new Object[cap];
			
			//copy data[0,manyItems-1] --> biggerData[0,manyItems-1]
			System.arraycopy(data, 0, biggerData, 0, manyItems);
			if(front>=rear){ //the part from [0,rear-1] is before the front 
				
				//copy data[0,rear] --> biggerData[data.length,data.length+rear]
				//after this, the real useful data is at biggerData[front,data.length+rear]
				System.arraycopy(data, 0, biggerData, data.length, rear);
			
				//Now, change rear; no need to change front
				rear +=data.length;
			}
			data = biggerData;
		}
	}
	
	/**
	 * Print the queue; for debugging purpose
	 */
	public void printQueue()
	{
		System.out.print("front="+front+",rear="+rear
				+",capacity="+data.length+",manyItems="+manyItems+": ");
		int cursor = front;
		for(int i=0;i<manyItems;i++){
			System.out.print("["+cursor+"]:"+data[cursor]+" ");
			cursor = (++cursor)%data.length;
		}
		System.out.println();
	}
	
	/**
	 * Test program
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
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
		
		System.out.println("\nTest circulating enqueue\n");
		for(int i=0;i<6;i++){
			int val = i+1;
			queue.enqueue(val);
			queue.printQueue();
		}
		
		System.out.println("\nTest circulating dequeue\n");
		while(!queue.isEmpty()){
			System.out.print("dequeue="+queue.dequeue()+" ");
			queue.printQueue();
		}
		
		System.out.println("\nTest allocating new space enqueue\n");
		for(int i=0;i<20;i++){
			int val = i+1;
			queue.enqueue(val);
			queue.printQueue();
		}
		
		System.out.println("\nTest dequeue after new space allocation\n");
		while(!queue.isEmpty()){
			queue.dequeue();
			queue.printQueue();
		}

	}
}