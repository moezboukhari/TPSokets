import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSocket extends Thread{
private PrintWriter pr;
private BufferedReader br;
	public static void main(String[] args) {
		new ClientSocket(); 
	}
	
public ClientSocket() {
		try {
			Socket s = new Socket("localhost", 1234);
			br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			pr=new PrintWriter(s.getOutputStream(),true);
			this.start();
			Scanner clavier=new Scanner(System.in);
			while (true) {
				System.out.println("entrer votre requette");
				String req=clavier.nextLine();
				pr.println(req);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

@Override
public void run() {
	try {
	String rep;
	while((rep=br.readLine()) != null){
		
			
			System.out.println("je lit la reponse du serveur "+rep);
		
	}
	
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
