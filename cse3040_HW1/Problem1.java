package cse3040_hw1_20171639;

import java.util.Scanner;

public class Problem1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("ASCII code teller. Enter a letter: ");
		String string = in.nextLine();
		
		String errorMessage = "You must input a single uppercase or lowercase alphabet.";

		if(string.length() > 1) {
			System.out.println(errorMessage);
			return;
		}
		
		char c = string.charAt(0);
		if(('a'<=c && c<='z') || ('A'<=c && c<='Z')) {
			//System.out.println("right index");
			System.out.printf("The ASCII code of %c is %d.\n",c,(int)(c));
		}
		else {
			//System.out.println("out of the boundary");
			System.out.println(errorMessage);
		}
		
		in.close();
		
	}

}
