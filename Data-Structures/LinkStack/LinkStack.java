
import java.util.EmptyStackException;

/**
 * Generic stack, implemented with generic linked list
 * 
 * @author Jacobo Giron
 */
public class LinkStack<E> implements MyStack<E>{
	//It has ONLY one instance variable, which is a generic node of type Snode.
	private SNode<E> top;
	
	//No argument constructor
	public LinkStack(){	top = null;}

	@Override
	public E pop() {
		if(top==null) return null;
		else{
			E result = top.getElement();
			top = top.getNext();
			return result;
		}
	}

	@Override
	public void push(E e) {	top = new SNode<E>(e,top);}

	@Override
	public E top() {
		if(isEmpty()) throw new EmptyStackException();
		return top.getElement();
	}

	@Override
	public int size() {
		return SNode.listLength(top);
	}

	@Override
	public boolean isEmpty() {
		return (top==null);
	}
	
	public static void main(String[] args){
		LinkStack<Integer> intStack = new LinkStack<Integer>();
		
		for(int i=10;i>=0;i--){
			intStack.push(i);
			System.out.println("Push e="+i+",inStack size="+intStack.size()+",top="+intStack.top());
		}
		
		while(!intStack.isEmpty()){
			System.out.println("intStack pop="+intStack.pop() +", intStack size="+intStack.size());
		}
	}
}