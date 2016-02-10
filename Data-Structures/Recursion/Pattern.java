
public class Pattern {

	public static void printPattern(int prefixspace,int star)
	{
		if(star<=0) return;
		printPattern(prefixspace,star/2);
		for(int i=0;i<prefixspace;i++)System.out.print("   ");
		for(int i=0;i<star;i++)System.out.print(" * ");
		System.out.println();
		printPattern(prefixspace+star/2,star/2);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("0");printPattern(0,0);
		System.out.println("2");printPattern(0,2);
		System.out.println("4");printPattern(0,4);
		System.out.println("8");printPattern(0,8);
		System.out.println("16");printPattern(0,16);
	}

}