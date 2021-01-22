package cse3040_hw2_20171639;
import java.util.Scanner;

interface IntSequenceStr {
	public abstract boolean hasNext();
	public abstract int next();
}

class BinarySequenceStr implements IntSequenceStr {
	private int num;
	private int[] binary;
	private int index=0;
	
	public BinarySequenceStr(int n) {
		num=n;
		binary = new int[num];
		while(num>0) {
			binary[index++] = (int)num % 2;
			num /= 2;
		}
		index-=1;
	}
	public boolean hasNext(){
		return (index>=0);
	}
	public int next() {

		return binary[index--];
	}
}
public class Problem7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");
		String str = in.nextLine();
		int num = Integer.parseInt(str);
		in.close();
		System.out.println("Integer: "+num);
		
		//convert input integer to binary number
		IntSequenceStr seq = new BinarySequenceStr(num);
		
		System.out.print("Binary number: ");
		while(seq.hasNext()) 
			System.out.print(seq.next());
		
		System.out.println("");
	}

}
