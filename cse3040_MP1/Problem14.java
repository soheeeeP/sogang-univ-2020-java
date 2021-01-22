package cse3040_mp1_20171639;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

interface ItemReader {
	static boolean fileToBox(String filename, FruitBox<Fruit> box) {
		BufferedReader br;
		String buffer, fruit;
		Double price;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			return false;
		}
		while(true) {
			try {
				while((buffer=br.readLine()) != null) {
					//debugging
					//System.out.println(buffer);
					fruit = buffer.split(" ")[0];
					price = Double.parseDouble(buffer.split(" ")[1]);
					
					box.add(new Fruit(fruit,price));
				}
				br.close();
				return true;
				
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
	}
}
class Fruit implements ItemReader, Comparable<Fruit>{

	private String fruit;
	private double price;
	
	public Fruit(String str,double d) {
		this.fruit = str;
		this.price = d;
	}
	public double getPrice() {
		return this.price;
	}
	public String toString() {
		return this.fruit + " " + this.price;
	}
	@Override
	public int compareTo(Fruit item) {
		// TODO Auto-generated method stub
		if(this.price > item.price)
			return 1;
		else if(this.price == item.price) {
			int compare = this.fruit.compareTo(item.fruit);
			if(compare>0) return 1;
		}
		return -1;
	}
}

class FruitBox<Fruit> implements ItemReader {
	private String line;
	private double price;
	private int minIdx, maxIdx;
	
	ArrayList<Fruit> list = new ArrayList<Fruit>();
	void add(Fruit item) {
		System.out.println(item);
		list.add(item);
		}

	public int getNumItems() { return list.size(); }
	public String getMaxItem() {
		double maxValue = Double.MIN_VALUE;
		int idx = 0;
		
		for(var i=0;i<list.size();i++) {
			line = list.get(i).toString();
			price = Double.parseDouble(line.split(" ")[1]);
			//System.out.println(price);
			if(price>maxValue) {
				maxValue=price;
				idx = i;
			}
		}
		this.maxIdx = idx;
		
		return list.get(idx).toString().split(" ")[0];
	}
	
	public double getMaxPrice() {
		line = list.get(maxIdx).toString();
		return Double.parseDouble(line.split(" ")[1]);
	}

	public String getMinItem() {
		double minValue = Double.MAX_VALUE;
		int idx = 0;
		
		for(var i=0;i<list.size();i++) {
			line = list.get(i).toString();
			price = Double.parseDouble(line.split(" ")[1]);
			//System.out.println(price);
			if(price<minValue) {
				minValue=price;
				idx = i;
			}
		}
		this.minIdx = idx;
		
		return list.get(idx).toString().split(" ")[0];
	}
	
	public double getMinPrice() {
		line = list.get(minIdx).toString();
		return Double.parseDouble(line.split(" ")[1]);		
	}
	
	public double getAvgPrice() {
		double sum = 0.0;
		
		for(var i=0;i<list.size();i++) {
			line = list.get(i).toString();
			price = Double.parseDouble(line.split(" ")[1]);
			sum += price;
		}
		return sum/(list.size());
	}


}
public class Problem14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FruitBox<Fruit> box = new FruitBox<>();
		
		boolean rv = ItemReader.fileToBox("input_prob14.txt",box);
		if (rv==false) return;
		
		box.add(new Fruit("orange",9.99));
	
		System.out.println("------------------");
		System.out.println("     Summary     ");
		System.out.println("------------------");
		
		System.out.println("number of items : "+box.getNumItems());
		System.out.println("most expensive item: "+box.getMaxItem()+" ("+box.getMaxPrice()+")");
		System.out.println("cheapest item: "+box.getMinItem()+" ("+box.getMinPrice()+")");
		System.out.printf("average price of items : %.2f",box.getAvgPrice());
	}

}
