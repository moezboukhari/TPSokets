import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1234);
			System.out.println("j'attend la connexion ..");
			Socket s=ss.accept();
			InputStream is = s.getInputStream();
			OutputStream os= s.getOutputStream();
			int nb=is.read();
			System.out.println("Nombre Reçu "+nb);

			int resp=nb*10;
			System.out.println("j'envoi la reponse ..");

			os.write(resp);
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
