package cse3040_mp1_20171639;

interface PalindromeChecker {
	static PalindromeChecker check(String str) {
		return new Palindrome(str);
	}
	static PalindromeChecker check(int num) {
		return new Palindrome(num);
	}
}

class Palindrome implements PalindromeChecker{
	
	private String palindromeStr;
	
	public Palindrome(String str) {
		// error handling for uppercase alphabets
		this.palindromeStr = str;
		isPalindrome();
	}
	public Palindrome(int num) {
		// error handling for negative integer
		this.palindromeStr = Integer.toString(num);
		isPalindrome();
	}
	public void isPalindrome() {
		int i = 0;
		int j = this.palindromeStr.length() - 1;
		
		while(i<j) {
			if(this.palindromeStr.charAt(i) != this.palindromeStr.charAt(j)) {
				System.out.println(this.palindromeStr+ " is not a palindrome.");
				return;
			}
			i += 1;
			j -= 1;
		}
		System.out.println(this.palindromeStr+ " is a palindrome.");
	}
}


public class Problem11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PalindromeChecker.check("abcde");
		PalindromeChecker.check("abcba");
		PalindromeChecker.check(1234);
		PalindromeChecker.check(12321);
	}

}