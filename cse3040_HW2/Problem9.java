package cse3040_hw2_20171639;

import java.lang.Math;
interface Distance {
	public abstract double getDist();
}
class Point {
	private double[] point;
	public Point() {}
	public Point(double [] d) {
		point = new double[d.length];
		for(int i=0;i<d.length;i++)
			point[i] = d[i];
	}
	public double coordinate(int idx) {
		return point[idx];
	}
	public int dimensionCnt() {
		return point.length;
	}
}

class EuclideanDistance {
	private double dist = 0.0;
	public void calcDist(Point p1, Point p2) {
		if (p1.dimensionCnt()!=p2.dimensionCnt()) {
			dist = -1.0;
			return;
		}

		for(int i=0;i<p1.dimensionCnt();i++) {
			dist += Math.pow((p1.coordinate(i)-p2.coordinate(i)), 2);
		}
		dist = Math.sqrt(dist);
		return;
	}
	public static EuclideanDistance getDist(Point p1, Point p2) {
		EuclideanDistance euDist = new EuclideanDistance();
		euDist.calcDist(p1, p2);
		return euDist;
	} 
	public String toString() {	
		return String.valueOf(dist);
	}
}

class ManhattanDistance {
	private double dist = 0.0;
	public void calcDist(Point p1, Point p2) {
		if (p1.dimensionCnt()!=p2.dimensionCnt()) {
			dist = -1.0;
			return;
		}

		for(int i=0;i<p1.dimensionCnt();i++) {
			dist += Math.abs(p1.coordinate(i)-p2.coordinate(i));
		}
		return;
	}
	public static ManhattanDistance getDist(Point p1, Point p2) {
		ManhattanDistance manDist = new ManhattanDistance();
		manDist.calcDist(p1, p2);
		return manDist;
	} 
	public String toString() {	
		return String.valueOf(dist);
	}
}
public class Problem9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(new double[] {1.0,2.0,3.0});
		Point p2 = new Point(new double[] {4.0,5.0,6.0});	
		System.out.println("Euclidean Distance: "+ EuclideanDistance.getDist(p1, p2));
		System.out.println("Manhattan Distance: "+ ManhattanDistance.getDist(p1, p2));
		
		Point p3 = new Point(new double[] {1.0,2.0,3.0});
		Point p4 = new Point(new double[] {4.0,5.0});
		System.out.println("Euclidean Distance: "+ EuclideanDistance.getDist(p3, p4));
		System.out.println("Manhattan Distance: "+ ManhattanDistance.getDist(p3, p4));
	}

}
