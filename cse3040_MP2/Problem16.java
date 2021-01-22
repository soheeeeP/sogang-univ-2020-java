package cse3040_mp2_20171639;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

interface ElementReader extends java.lang.Comparable<Element>{
	static ArrayList<Element> readElements(String filename) {
		ArrayList<Element> arr = new ArrayList<>();
		
		BufferedReader br;
		String buffer;
		
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch(FileNotFoundException e) {
			return null;
		}
		while(true) {
			try {
				while((buffer=br.readLine())!=null) {
					String str[] = buffer.split(" ");
					Element elem = new Element(str[0],Double.parseDouble(str[1]));
					arr.add(elem);					
				}
				br.close();
				return arr;
			} catch (IOException e) {
				return null;
			}
		}
	}
}

class Element implements ElementReader, Iterator{
	private String fruit;
	private double price = 0.0;
	
	public Element(String fruit,double price) {
		this.fruit = fruit;
		this.price = price;
	}
	public String fruitName() {
		return this.fruit;
	}
	public double fruitPrice() {
		return this.price;
	}
	@Override
	public int compareTo(Element o) {
		// TODO Auto-generated method stub
		// sorting
		if(this.price > o.price) {
			return 1;
		}
		else if(this.price < o.price)
			return -1;
		
		// if the fruits have the same price, sort in the alphabetical order
		else {
			//System.out.println(this.fruit+" : "+ this.fruit.compareTo(o.fruit));
			if(this.fruit.compareTo(o.fruit) < 0) return -1;
			return 0;
		}
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString() {
		return this.fruit + " " + this.price;
	}
	public Object next() {
		return this.toString();
		
	}
}

public class Problem16 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Element> list = ElementReader.readElements("input.txt");
		
		if (list==null) {
			System.out.println("Input file not found.");
			return;
		}
				
		Collections.sort(list);
		Iterator<Element> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}

}
