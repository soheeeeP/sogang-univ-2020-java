package cse3040_hw1_20171639;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter a text: ");
		String textString = in.nextLine();
		
		int cnt=0;	//variable which counts the instances of the given string in the text
		
		while(true) {
			System.out.print("Enter a letter: ");
			String letter = in.nextLine();
			
			//if the user inputs an empty string, print error message and ask again
			if(letter.length() < 1) {
				System.out.println("You must enter a string.");
				continue;
			}
			
			//length of string
			int stride = letter.length();
			
			//counts how many instances of the given string are there in the text
			cnt=0;
			for(int i=0;i<textString.length()-stride+1;i++) {
				if(textString.substring(i, i+stride).equals(letter)) {
					//System.out.printf("[%d]: from %d to %d\n",cnt,i,i+stride);
					cnt++;
				}
			}
			
			System.out.printf("There are %d instances of \"%s\".\n",cnt,letter);
			break;
		}
		in.close();
		return;
	}

}
