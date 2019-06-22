import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
try {
	System.out.println("essayer connexion vers le serveur");
	Socket s = new Socket("localhost",1234);
	InputStream is = s.getInputStream();
	OutputStream os = s.getOutputStream();
	Scanner scanner = new Scanner(System.in);
	System.out.println("entrer un nombre");
	int nb=scanner.nextInt();
	System.out.println("envoie du donnés");

	os.write(nb);
	System.out.println("j'attend la reponse .....");

	int reponse=is.read();
	System.out.println("Réponse reçu"+reponse);


} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}

}
