package cse3040_hw1_20171639;

import java.util.Scanner;
public class Problem2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int num;
		
		int cnt = 0;
		int target = (int)(100*Math.random())+1;
		
		//set range of the random number
		int startIdx = 1;
		int endIdx = 100;
		
		//System.out.printf("random number is %d\n",target);
		
		while(true) {
			System.out.printf("[%d] Guess a number (%d-%d): ",cnt+1,startIdx,endIdx);
			num = in.nextInt();
			
			if((startIdx<=num && num<=endIdx)) { 
				//if the user input is correct, print message and terminate the program
				if(num==target) {
					System.out.printf("Correct! Number of guesses: %d\n", cnt+1);
					break;
				}
				//if the user input is larger than the ans, print message and reset endIdx
				else if(num>target) {
					System.out.println("Too large!");
					endIdx = num-1;
				}
				//if the user input is smaller than the ans, print message and reset startIdx
				else {
					System.out.println("Too small!");
					startIdx = num+1;
				}
				cnt++;
			}
			//if the user input is not in the given range, print error message and ask again
			else {
				System.out.println("Not in range!");
			}
			
		}
		in.close();
		return;
		
	}

}
