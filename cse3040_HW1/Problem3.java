package cse3040_hw1_20171639;

import java.util.Scanner;

public class Problem3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter a text: ");
		String textString = in.nextLine();
		
		int cnt=0;		//variable which counts the instances of the given letter in the text
		
		while(true) {
			System.out.print("Enter a letter: ");
			String letter = in.nextLine();
			
			//if the user enters nothing or more than one chars, print error message and ask again
			if(letter.length() != 1) {
				System.out.println("You must enter a single letter.");
				continue;
			}
			
			char c = letter.charAt(0);
			
			//counts how many instances of the given letter are there in the text
			cnt=0;
			for(int i=0;i<textString.length();i++) {
				if(textString.charAt(i)==c) cnt++;
			}
			
			System.out.printf("There are %d %c's in the text.\n",cnt,c);
			break;
		}
		in.close();
		return;
	}

}
