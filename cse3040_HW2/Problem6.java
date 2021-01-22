package cse3040_hw2_20171639;

interface IntSequence {
	public abstract boolean hasNext();
	public abstract int next();
}

// class that implements the interface
class FibonacciSequence implements IntSequence {
	private int i,j,n;
	public FibonacciSequence() {
		n=-1;	//round
		i=0;
		j=1;
	}
	// implement all methods in the interface
	public boolean hasNext() {
		return (n++)!=20;
	}
	public int next() {
		if(n==0) return 0;
		if(n==1) return 1;
		int result= i+j;
		
		i=j;
		j=result;
		
		return result;
	}
}
public class Problem6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 IntSequence seq = new FibonacciSequence();
		 for(int i=0;i<20;i++) {
			 if(seq.hasNext()==false) break;
			 System.out.print(seq.next()+" ");
		 }
		 System.out.println(" ");
	}

}
