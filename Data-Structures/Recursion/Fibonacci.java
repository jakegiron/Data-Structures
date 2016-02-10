
/**
 * @author Jacobo Giron
 */
public class Fibonacci {

	/**
	 * Find the kth Fibonacci number F_k using binary recursion.
	 * @param k
	 * @return
	 */
	public int FibBinaryRecursive(int k)
	{
		if(k<0) throw new IllegalArgumentException("k should be nonnegative.");
		
		if(k==0) return 0;
		else if(k==1) return 1;
		else return (FibBinaryRecursive(k-1)+FibBinaryRecursive(k-2));
	}
	 
	/**
	 * Find the kth Fibonacci number F_k using linear recursion. 
	 * @param k
	 * @return int array where [0] is Fk-1 and [1] is Fk.
	 */
	public int[] FibLinearRecursive(int k)
	{
		if(k<0) throw new IllegalArgumentException("k should be nonnegative.");
		if(k==0) return new int[]{0,0};
		else if(k==1) return new int[]{0,1};
		else {
			int[] rc= FibLinearRecursive(k-1);
			return new int[]{rc[1], rc[0]+rc[1]};
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Fibonacci fib = new Fibonacci();
		
		for(int i=0;i<30;i++){
			System.out.println("k="+i+",FibBinary["+i+"]="+fib.FibBinaryRecursive(i)
					+", FibLinear["+i+"]="+fib.FibLinearRecursive(i)[1]);
		}

	}

}