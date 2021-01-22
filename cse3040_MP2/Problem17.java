package cse3040_mp2_20171639;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

interface MapManager{
	static Map<String,Double> readData(String filename) {
				
		CustomMap1<String,Double> map = new CustomMap1<>();
		
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
					map.put(str[0], Double.parseDouble(str[1]));
				}
				br.close();
				return map;
			} catch (IOException e) {
				return null;
			}
		}
	}

	static Map<String,Double> sortByValue(Map<String,Double> map){
		
		List<Entry<String,Double>> list = new ArrayList<Entry<String,Double>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String,Double>>(){

			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				// sorting
//				if(o1.getKey().compareTo(o2.getKey())<0)
//					return -1;
//				else if(o1.getKey().compareTo(o2.getKey())>0)
//					return 1;
//				else
//					return 0;
				return o1.getValue().compareTo(o2.getValue());
			}
			
		});	
		return map;	
	};
}

class CustomMap1<K,V> extends TreeMap<K,V>{
	public CustomMap1() { super(); }
	public String toString() {
		List<Map.Entry<K,V>> list = new ArrayList<>(this.entrySet());

		Iterator<Map.Entry<K, V>> it = list.iterator();
		String str = "";
		Map.Entry<K, V> e;
		while (it.hasNext()) {
			e = it.next();
			str += e.getKey() + " " + e.getValue() + "\n";
		}
		return str.substring(0, str.length() - 1);
	}
}


public class Problem17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Double> map = MapManager.readData("input.txt");
		if (map==null) {
			System.out.println("Input file not found");
			return;
		}
	
		System.out.println(map);
	}

}
