package cse3040_FP;

import java.io.*;
import java.net.*;

class ServerReceiver extends Thread{
	Socket socket;
	File file;
	DataInputStream in;
	DataOutputStream out;
	
	String msg = null;
	String username;

	ServerReceiver(Socket socket,File file){
		this.socket = socket;
		this.file = file;
		
		while(true) {
			try {
				InputStream in = this.socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				msg = dis.readUTF();
				//System.out.println(msg);
				String[] msgArr = msg.split("\t");
				
				String filePath = System.getProperty("user.dir");
				BufferedReader br = new BufferedReader(new FileReader(filePath+"/books.txt"));

				String command = msgArr[0];
				String line, title, author, status;
				
				int state, compareTitle, compareAuthor, compareStatus;

				// send message from server to client
				OutputStream out = this.socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				// dummy string line to modify "books.txt"
				String dummy = "";
				StringBuffer sb = new StringBuffer();
				
				switch(command) {
					case "username":
						this.username = msgArr[1];
						break;
						
					case "add":
						state = 1;
						while((line=br.readLine())!=null) {
							//System.out.println(line.split("\t").length);
							title = line.split("\t")[0];
							author = line.split("\t")[1];
							 
							compareTitle = title.compareToIgnoreCase(msgArr[1]);
							compareAuthor = author.compareToIgnoreCase(msgArr[2]);
							 
							if((compareTitle==0)&&(compareAuthor==0)) state=0;
						}
						//add a new book
						if(state==1) {
							//file write
							BufferedWriter bw = new BufferedWriter(new FileWriter(filePath+"/books.txt",true));
							bw.write(msgArr[1]+"\t"+msgArr[2]+"\t"+"-");
							bw.newLine();
							bw.flush();
							dos.writeUTF("A new book added to the list.");
						}
						//the book already exists
						else if(state==0) {
							dos.writeUTF("The book already exists in the list.");
						}
						break;
					case "borrow":
						while((line=br.readLine())!=null) {
							title = line.split("\t")[0];
							author = line.split("\t")[1];
							status = line.split("\t")[2];
							
							compareTitle = title.compareToIgnoreCase(msgArr[1]);
							//검색한 도서 제목을 찾음
							if(compareTitle==0){
								//도서가 대여 가능한 상태
								if(status.equals("-")) {
									dummy += (title+"\t"+author+"\t"+this.username+"\r\n");
									dos.writeUTF("You borrowed a book. - "+title);
								}
								//도서가 대여 중인 상태
								else {
									dos.writeUTF("The book is not available.");
								}
							}
							
							else {
								dummy += (line+"\r\n");
							}
							
						}	
						//System.out.println("====\n"+dummy+"====");
						FileWriter fw = new FileWriter(System.getProperty("user.dir")+"/books.txt");
						fw.write(dummy);
						fw.close();
						
						break;
						
					case "return":
						while((line=br.readLine())!=null) {
							title = line.split("\t")[0];
							author = line.split("\t")[1];
							status = line.split("\t")[2];
							
							compareTitle = title.compareToIgnoreCase(msgArr[1]);
							if(compareTitle==0) {
								//도서가 반납 가능한 상태
								if(status.equals(username)) {
									//System.out.println("return available");
									dummy += (title+"\t"+author+"\t-\r\n");
									dos.writeUTF("You returned a book. - "+title);
								}
								//사용자가 도서를 대출한 적이 없는 상태
								else {
									dos.writeUTF("You did not borrow the book.");
								}
							}
							else {
								dummy += (line+"\r\n");
							}
						}
						
						fw = new FileWriter(System.getProperty("user.dir")+"/books.txt");
						fw.write(dummy);
						fw.close();
						
						break;
					case "info":
						int infoIdx = 0;
						while((line=br.readLine())!=null) {
							title = line.split("\t")[0];
							author = line.split("\t")[1];
							status = line.split("\t")[2];
							
							if(status.equals(this.username)) {
								infoIdx += 1;
								sb.append(Integer.toString(infoIdx)+". "+title+", "+author+"\r\n");
							}
						}
						sb.setCharAt(sb.length()-1,'\0');
						dummy = sb.toString();
						dos.writeUTF("You are currently borrowing "+Integer.toString(infoIdx)+" books:\n"+dummy);
						break;
						
					case "search":
						int searchIdx = 0;
						while((line=br.readLine())!=null) {
							title = line.split("\t")[0];
							author = line.split("\t")[1];
							
							boolean searchTitle = title.contains(msgArr[1]);
							boolean searchAuthor = author.contains(msgArr[1]);
							if(searchTitle || searchAuthor) {
								searchIdx += 1;
								sb.append(Integer.toString(searchIdx)+". "+title+", "+author+"\r\n");
							}
						}
						sb.setCharAt(sb.length()-1,'\0');
						dummy = sb.toString();
						dos.writeUTF("Your search matched "+Integer.toString(searchIdx)+" results.\n"+dummy);
						break;
						
					default:
						break;
				}
				
				
			} catch (IOException e) {
				return;
				//e.printStackTrace();
			}
		}
	}
}
// thread를 사용, multiple client에 대한 처리를 수
public class Server implements Runnable {

	ServerSocket serverSocket = null;
	Socket socket = null;
	Thread[] threadArr = null;
	
	File file = null;
	BufferedReader in = null;
	
	// constructor
	public Server(int portNum) {
		try {
			serverSocket = new ServerSocket(portNum);
			threadArr = new Thread[10];
			//System.out.println("Server is ready.");
			//create txt file
			
			String filePath = System.getProperty("user.dir");
			try {
				file = new File(filePath+"/books.txt");
				boolean success = file.createNewFile();
				if(!success)
					System.out.println("file already exists.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		} catch (IOException e){
			//e.printStackTrace();
			System.out.println("Cannot create new Serversocket.");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//command line에서 port 번호 받기
		int len = args.length;
		if(len != 1) {
			System.out.println("Please give the port number as an argument.");
			return;
		}
	
		int portNum = Integer.parseInt(args[0]);
		Server server = new Server(portNum);
		server.start();
		
	}
	public void start() {
		for(int i=0;i<threadArr.length;i++) {
			threadArr[i] = new Thread(this);
			threadArr[i].start();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				// client의 요청을 받을 때까지 대기			
				socket = serverSocket.accept();
				System.out.println("Connection request from..." + socket.getLocalAddress()+":"+socket.getLocalPort());
				
				//thread별로 따로 처리하기 (serverreceiver 사용)
				ServerReceiver re = new ServerReceiver(socket,file);
				socket.close();
			} catch(IOException e) {
				//e.printStackTrace();
				System.out.println("Cannot connect with client");
			}
		}
	}

}
