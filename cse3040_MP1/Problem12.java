package cse3040_mp1_20171639;

interface SubsequenceChecker {
	static SubsequenceChecker check(String str1, String str2) {
		return new Subsequence(str1,str2);
	}
}

class Subsequence implements SubsequenceChecker {
	private String givenStr;
	private String subStr;
	
	public Subsequence(String a, String b) {
		this.givenStr = a;
		this.subStr = b;
		
		isSubsequence();
	}
	public void isSubsequence() {
		int i1,	i2;							// start idx
		int j1, j2;							// end idx
		int cnt = 0;
		
		i1 = i2 = 0;
		j1 = this.givenStr.length() - 1;
		j2 = this.subStr.length() - 1;
		
		int[] idx = new int[j2+1];

	
		while(true) {
			if((i1>j1) || (i2>j2))
				break;
			
			if(this.givenStr.charAt(i1) == this.subStr.charAt(i2)) {
				cnt += 1;
				idx[i2] = i1;
				i1 += 1;
				i2 += 1;
			}
			else {
				i1 += 1;
			}
		}
				
		if(cnt == j2+1) {
			System.out.println(this.subStr+" is a subsequence of "+this.givenStr);
			for(int x=0;x<=j2;x++)
				System.out.print(idx[x]+" ");
			System.out.println();
		}
		else {
			System.out.println(this.subStr+" is not a subsequence of "+this.givenStr);
		}
	}
}
public class Problem12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "pads");
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "padsx");
		
	}

}