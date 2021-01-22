package cse3040_mp1_20171639;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

interface MyFileReader {
	static boolean readDataFromFile(String filename, ArrayList<Item> list) {
		BufferedReader br;
		String buffer;
		
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			return false;
		}
		while(true) {
			try {
				int i;
				String word;
				while((buffer=br.readLine()) != null) {
					String str[] = buffer.toLowerCase().split(" ");
					for (String s: str) {
						//System.out.println(list.size());
						for(i=0;i<list.size();i++) {
							Item item = list.get(i);
							word = item.getWord();
							//System.out.println(s+" == "+word);
							if(s.equals(word)) {
								//System.out.println(s+" .. repeating");
								item.addCnt();
								break;
							}
						}
						if(i==list.size())
							list.add(new Item(s,1));
					}
				}
				br.close();
				return true;
			} catch (IOException e) {
				return false;
			}
		}
	}
}
class Item implements MyFileReader{
	private String word;
	private int cnt = 0 ;
	
	public Item(String word, int cnt) {
		this.word = word;
		this.cnt += cnt;
	}
	public String getWord() {
		return this.word;
	}
	public void addCnt() {
		this.cnt += 1;
	}
	public String toString() {
		return this.word+ " "+this.cnt;
	}
}
public class Problem15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Item> list = new ArrayList<>();
		boolean rv = MyFileReader.readDataFromFile("input_prob15.txt", list);
		
		if(rv==false) {
			System.out.println("Input file not found.");
		}
		for(Item it: list) System.out.println(it);
	
	}

}
