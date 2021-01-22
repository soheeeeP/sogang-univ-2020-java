package cse3040_FP;

//input stream
import java.io.*;
import java.util.*;
import java.net.*;

public class Client {

	ServerSocket serverSocket = null;
	Socket socket = null;
	
	//constructor
	public Client(String IpAddr, int portNum) {
		try {
			socket = new Socket(IpAddr,portNum);
		} catch(IOException e){
			System.out.println("Connection establishment failed.");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int len = args.length;
		if(len!=2) {
			System.out.println("Please give the IP address and port number as arguments.");
			return;
		}
		
		String IpAddr = args[0];
		int portNum = Integer.parseInt(args[1]);
		Client client = new Client(IpAddr, portNum);
		
		Scanner in = new Scanner(System.in);
		boolean logIn = false;
		String userID = null;
		
		while(true) {
			try {
				OutputStream out = client.socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				//이 내용을 server측에서 read
				//dos.writeUTF();
				
				// client logout mode
				if (logIn == false) {
					String errorMsg = "UserID must be a single word with lowercase alphabets and numbers.";
					while(true) {
						System.out.print("Enter UserID>> ");
						String inputID = in.nextLine();
						int i;
						for(i=0;i<inputID.length();i++) {
							char c = inputID.charAt(i);
							if(c==' ') break;
							if(('a'<=c && c<='z')||('0'<=c && c<='9')) continue;
							else break;
						}
						if(i==inputID.length()) {
							userID = inputID;
							logIn = true;
							dos.writeUTF("username\t"+userID);
							break;
						}
						else
							System.out.println(errorMsg);
					}
				}
				// client login mode
				else {
					while(true) {
						System.out.print(userID+">> ");
						String command = in.nextLine();
						String title = null, author = null, str = null;
						
						DataInputStream dis = new DataInputStream(client.socket.getInputStream());
						switch(command) {
							case "add":
								System.out.print("add-title> ");
								title = in.nextLine();
								if(title.equals("")) break;
									
								System.out.print("add-author> ");
								author = in.nextLine();
								if(author.equals("")) break;
								
								dos.writeUTF(command+"\t"+title+"\t"+author);
								System.out.println(dis.readUTF());
								break;
								
							case "borrow":
								System.out.print("borrow-title> ");
								title = in.nextLine();
								if(title.equals("")) break;
								
								dos.writeUTF(command+"\t"+title);
								System.out.println(dis.readUTF());
								break;
								
							case "return":
								System.out.print("return-title> ");
								title = in.nextLine();
								if(title.equals("")) break;
								
								dos.writeUTF(command+"\t"+title);
								System.out.println(dis.readUTF());
								break;
								
							case "info":
								dos.writeUTF(command);
								System.out.println(dis.readUTF());
								break;
								
							case "search":
								System.out.print("search-string> ");
								str = in.nextLine();
								if(str.equals("")) break;
								
								if(str.length()<=2) {
									System.out.println("Search string must be longer than 2 chararcters.");
								}
								else {
									dos.writeUTF(command+"\t"+str);
									System.out.println(dis.readUTF());
								}
								break;
								
							default:
								System.out.println("[available commands]");
								System.out.println("add: add a new book to the list of books.");
								System.out.println("borrow: borrow a book from the library.");
								System.out.println("return: return a book to the library.");
								System.out.println("info: show list of books I am currently borrowing.");
								System.out.println("search: search for books.");
								break;
						}
					}
				}
				
			} catch(IOException e) {
				//e.printStackTrace();
				System.out.println("OutputStream error.");
			}
		}
	}

}
