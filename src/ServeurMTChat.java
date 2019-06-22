import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServeurMTChat extends Thread {
	private List<Socket> sockets =new ArrayList<>();
	static int numClient = 0;
	private int nbrSecret;
	private Boolean fin=false;
	private String IpGagnant;

	public static void main(String[] args) {
		new ServeurMTChat().start();
	}

	@Override
	public void run() {
		try {
			// nbrSecret = (int) Math.random()*1000;
		//	nbrSecret = new Random().nextInt(1000);
			System.out.println("demarrage serveur");
			//System.out.println("le nombre secret est :" + nbrSecret);
			ServerSocket ss = new ServerSocket(1234);
			
			while (true) {
				Socket s = ss.accept();
				sockets.add(s);
				
				++numClient;
				new Conversation(s,numClient).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void braodcastMessgae(String message,Socket source){
	sockets.forEach(s->{
		if (s!=source){
		try {
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
			pw.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	});
}
	class Conversation extends Thread {
		private Socket socket;
private int client;
		public Conversation(Socket socket,int Client) {
			super();
			this.socket = socket;
			this.client=Client;
		}

		@Override
		public void run() {
			try {
				InputStream is = socket.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(is);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				OutputStream os = socket.getOutputStream();
				String IP = socket.getRemoteSocketAddress().toString();
				PrintWriter pw = new PrintWriter(os, true);				
				System.out.println("Client" + numClient + " IP =" + IP);
				
				pw.println("bienvenu Client numero : " + client);
				//pw.println("Devine le nombre secret entre 0 et 1000");
				while (true) {
					String req = bufferedReader.readLine();
					braodcastMessgae("<clien"+client+" Ip = "+IP+"> :"+  req,socket);
						
					//pw.println("length" + req.length());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
