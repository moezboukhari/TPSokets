import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServeurMT0 extends Thread {
	static int numClient = 0;
	private int nbrSecret;
	private Boolean fin=false;
	private String IpGagnant;

	public static void main(String[] args) {
		new ServeurMT0().start();
	}

	@Override
	public void run() {
		try {
			// nbrSecret = (int) Math.random()*1000;
			nbrSecret = new Random().nextInt(1000);
			System.out.println("demarrage serveur");
			System.out.println("le nombre secret est :" + nbrSecret);
			ServerSocket ss = new ServerSocket(1234);
			while (true) {
				Socket s = ss.accept();
				++numClient;
				new Conversation(s).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class Conversation extends Thread {
		private Socket socket;

		public Conversation(Socket socket) {
			super();
			this.socket = socket;
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
				
				pw.println("bienvenu Client numero : " + numClient);
				pw.println("Devine le nombre secret entre 0 et 1000");
				while (true) {
					String req = bufferedReader.readLine();
					System.out.println("Requette  " + req + " client" + numClient + " IP =" + IP);
					int nb=Integer.parseInt(req);
					if (fin==false){
						if (nb<nbrSecret){
							pw.println("votre numero est petit");
						}
							else if(nb>nbrSecret)
							{
								pw.println("votre numero est grand");
							}
							else
							{
								fin=true;
								System.out.println("Bravo Client" + numClient + " IP =" + IP);
								pw.println(" BRAVO GAGNANT !!!!");
								IpGagnant=IP;
							}
						
					}
					else{
						pw.println(" Jeux terminé , Gagnant!" +IpGagnant);
					}
						
					//pw.println("length" + req.length());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
