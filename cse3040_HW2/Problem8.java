package cse3040_hw2_20171639;

interface Shape {
	public abstract double getArea();
}

class Circle implements Shape{
	private double radius;
	public Circle(double d) {
		radius = d;
	}
	public double getArea() {
		return radius*radius* Math.PI;
	}
}
class Square implements Shape{
	private double len;
	public Square(double d) {
		len = d;
	}
	public double getArea() {
		return len*len;
	}
}
class Rectangle implements Shape{
	private double len1,len2;
	public Rectangle(double d1, double d2) {
		len1 = d1;
		len2 = d2;
	}
	public double getArea() {
		return len1*len2;
	}
	
}

public class Problem8 {
	public static double sumArea(Shape[] arr) {
		double area = 0.0;
		
		for(int i=0;i<arr.length;i++)
			area += arr[i].getArea();
		return area;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape[] arr = { new Circle(5.0), new Square(4.0), 
				new Rectangle(3.0, 4.0), new Square(5.0) };
		System.out.println("Total area of the shapes is: "+ sumArea(arr));
	}

}
