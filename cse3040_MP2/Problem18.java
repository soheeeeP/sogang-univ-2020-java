package cse3040_mp2_20171639;

import java.util.Map;

public class Problem18 {

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
