
public class MyMath {

	/**
	 * gdc(Greatest Common Divisor) 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int gcd(int x, int y){
		if(y==0) return x;
		else return gcd(y,x%y);
	}
	
	 /**
	  * Find the minimum value in an Array. 
	  * @param array
	  */
	 public static int findMin(int[] array){
		 if(array==null||array.length==0){
			 System.out.println("Invalid input");
			 return (-1);
		 }
		 int minval = findMinBinaryRecursive(array,0,array.length-1);
		 return minval;
	 }
	 
	 /**
	  * @param array
	  * @param startidx
	  * @param endidx
	  * @return
	  */
	 private static int findMinBinaryRecursive(int[] array, int startidx, int endidx)
	 {
		 if(startidx==endidx) return array[startidx];
		 
		 int mididx = (startidx+endidx)/2;
		 int v1 = findMinBinaryRecursive(array,startidx,mididx);
		 int v2 = findMinBinaryRecursive(array,mididx+1,endidx);
		 
		 return (v1<v2?v1:v2);
		 
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array1=new int[]{4,5,6,6,10,9,21};
		System.out.println("minval="+findMin(array1)+" in array1");
		int[] array2=new int[]{2,5,6,1,10,9,100,21};
		System.out.println("minval="+findMin(array2)+" in array2");
		
		int[] array3=new int[]{};
		System.out.println("minval="+findMin(array3)+" in array3");
		
		int[] array4=new int[]{10};
		System.out.println("minval="+findMin(array4)+" in array4");
		
		System.out.println("gcd(1,2)="+gcd(1,2));
		System.out.println("gcd(2,3)="+gcd(2,3));
		System.out.println("gcd(36,48)="+gcd(36,48));
		System.out.println("gcd(94,12)="+gcd(94,12));
		System.out.println("gcd(94,-12)="+gcd(94,-12));
	}

}