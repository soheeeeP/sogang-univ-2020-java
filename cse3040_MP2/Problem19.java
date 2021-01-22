package cse3040_mp2_20171639;

import java.util.*;
import java.io.*;
import java.net.*;

interface BookReader extends java.lang.Comparable<BookInfo> {
	static ArrayList<BookInfo> readBooks(String urlLink){
		ArrayList<BookInfo> books = new ArrayList<BookInfo>();
		
		ArrayList<String> lines = new ArrayList<>();
		
		BufferedReader br = null;
		String buffer = "";
		URL url = null;
		
		try {
			url = new URL(urlLink);
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			
			while((buffer=br.readLine())!=null) {
				if(buffer.trim().length()>0) {
					lines.add(buffer);
					//if (buffer.contains("div class=\"count\""))
					//	System.out.println(buffer);
				}

			}
			
			br.close();
			
			// parsing HTML
			int status = 0;
			int begin,end;
			
			int rank = 0;
			double price = 0.0;
			String title = null, author = null;

			for(int i=0;i<lines.size();i++) {
				String l = lines.get(i);
				if(status==0 && l.contains("div class=\"resultsListContainer topXList favIconContainer\"")) {
					status = 1;
				} else if (status==1 && l.contains("div class=\"row topX-row\"")) { 
					status = 2;
				} else if (status==2 && l.contains("div class=\"count\"")) {
						//scrap rank info
					begin = l.indexOf("\">")+2;
					end = l.indexOf("</div>");
					rank = Integer.parseInt(l.substring(begin, end));
					
					status = 3;
				} else if (status==3) {
					//title
					if(l.contains("a title=\"\" class=\" \" onclick=\"set_cookie")) {
						begin = l.indexOf("\">")+2;
						end = l.indexOf("</a>");
						title = l.substring(begin, end);
					}
					
					//author
					if(l.contains("div class=\"product-shelf-author contributors\"")) {
						l = l.substring(l.indexOf("by")+2);
						begin = l.indexOf("\">")+2;
						end = l.indexOf("</a>");
						author = l.substring(begin, end);
					}
					
					//price
					if(l.contains("current link")) {
						begin = l.indexOf("$")+1;
						end = l.indexOf("</a>");
						price = Double.parseDouble(l.substring(begin, end));
						
						books.add(new BookInfo(rank,title,author,price));
						status = 1;
					}
				}	
			}
			return books;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

class BookInfo implements BookReader{
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
		//sorting 
		if(this.rank<o.rank)
			return 1;
		else if (this.rank>o.rank)
			return -1;
		else
			return 0;
	}
	public String toString() {
		return "#" + this.rank + " " + this.title + ", " + this.author + ", $" + this.price;
	}
	
}
public class Problem19 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<BookInfo> books;
		books = BookReader.readBooks("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		
		Collections.sort(books);
		for(int i=0;i<books.size();i++) {
			BookInfo book = books.get(i);
			System.out.println(book);
		}
	}

}
