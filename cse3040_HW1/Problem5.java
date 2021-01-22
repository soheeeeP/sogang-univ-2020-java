package cse3040_hw1_20171639;

import java.util.Scanner;
public class Problem5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter exam scores of each student.");
		
		int firstPlace = 0;
		int secondPlace = 0;
		
		int score[] = new int[5];
		for(int i=0;i<5;i++) {
			System.out.printf("Score of student %d: ",i+1);
			score[i]=in.nextInt();
			
			//reset 1st, 2nd place students' idx
			
			if(score[i]>score[firstPlace]) {
				secondPlace = firstPlace;
				firstPlace = i;
			}
			else if((score[secondPlace]<score[i] && score[i]<score[firstPlace])) {
				secondPlace = i;
			}
			
			//System.out.printf("%d %d\n", firstPlace+1, secondPlace+1);
				
		}
		
		System.out.printf("The 1st place is student %d with %d points.\n",firstPlace+1,score[firstPlace]);
		System.out.printf("The 2nd place is student %d with %d points.\n", secondPlace+1,score[secondPlace]);
		
		return;

	}

}
