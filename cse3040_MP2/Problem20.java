package cse3040_mp2_20171639;

import java.util.*;
import java.io.*;
import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

interface BookReader extends java.lang.Comparable<BookInfo> {
	static ArrayList<BookInfo> readBooksJsoup(String urlLink){
		ArrayList<BookInfo> books = new ArrayList<BookInfo>();
		
		ArrayList<String> lines = new ArrayList<>();
		BufferedReader br = null;
		String buffer = "";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(urlLink).get();
			
			Elements bestsellers = doc.select("div.resultsListContainer");
			Elements booksList = bestsellers.select("div.row");
			
			Elements rank = booksList.select("div.count");
			Elements title = booksList.select("h3.product-info-title").select("a[href]");
			Elements author = booksList.select("div.product-shelf-author").select("a[href]");
			Elements price = booksList.select("div.product-shelf-pricing").select("td.buy-new").select("a[href]");
			
			int r = 0;
			double p = 0.0;
			
			for(int i=0;i<title.size();i++) {
				r = Integer.parseInt(rank.eq(i).text());
				p = Double.parseDouble(price.eq(i).text().substring(1));
				
				books.add(new BookInfo(r,title.eq(i).text(),author.eq(i).text(),p));
			}
			return books;
			
			
		} catch (IOException e) {
			e.getMessage();
			return null;
		}
	}
}

class BookInfo implements BookReader {
	private int rank;
	private String title, author;
	private double price;
	
	public BookInfo(int rank, String title, String author, double price) {
		this.rank = rank;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	@Override
	public int compareTo(BookInfo o) {
		// TODO Auto-generated method stub
		if(this.rank<o.rank) return 1;
		else if(this.rank>o.rank) return -1;
		else return 0;
	}
	public String toString() {
		return "#" + this.rank + " " + this.title + ", " + this.author + ", $" + this.price;
	}
}
public class Problem20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<BookInfo> books;
		books = BookReader.readBooksJsoup("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		
		Collections.sort(books);
		for(int i=0;i<books.size();i++) {
			BookInfo book = books.get(i);
			System.out.println(book);
		}
	}

}
