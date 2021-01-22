package cse3040_mp1_20171639;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Text {
	private String filename;
	public boolean readTextFromFile(String filename){
		this.filename = filename;
		try {
			FileInputStream input = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Input File not found.");
			return false;
		}
		
		return true;
	}
	public int countChar(char c) throws IOException {
		int cnt = 0;
		
		BufferedReader br = new BufferedReader(new FileReader(this.filename));
		
		while(true) {
			String line = br.readLine();
			if (line==null) break;
			for(int i=0;i<line.length()-1;i++) {
				if((c==line.charAt(i)) || (c==line.charAt(i)+32)){
					cnt++;
				}
			}
		}
		br.close();
	
		return cnt;
	}
}
public class Problem13 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Text t = new Text();
		if(t.readTextFromFile("input_prob13.txt")) {
			for(char c='a'; c<='z'; c++) {
				System.out.println(c+": "+t.countChar(c));
			}
		}
	}

}
