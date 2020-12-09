package serversocket;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import entities.Car;

public class SocketServer {

	public static final String risorse = System.getProperty("user.dir") + File.separator + "resources" + File.separator;

	private static final int porta = 8080;

	private static final String stop = "/stop";

	public static void main(String args[]) {
		SocketServer server = new SocketServer();
		server.await();
		//Car c = null;
	}

//

	public void await() {
		ServerSocket server = null;

		try {
			server = new ServerSocket(porta, 1, InetAddress.getByName("localhost"));// oggetto che si mette ciclicamente
																					// in ascolto e alla riga 40 il
																					// programma si ferma fino a quando
																					// non arriva dei dati alla porta
																					// 8080
			boolean stop = false;
			while (!stop) {

				System.out.println("Server in ascolto alla porta 8080");// zona memoria sistema operativo
				System.out.println("Prima della richiesta...");
				Socket connessione = server.accept();// alla porta 8080 arriva una richiesta get http
				System.out.println("Dopo la richiesta...");
				InputStream input = connessione.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(input));
				System.out.println("Dopo la creazione di BufferedReader...");
				String str=null;
				String input_str = br.readLine();
				String[] input_split = input_str.split(" ");
				String richiesta = input_split[1].substring(1);
				System.out.println("La richiesta grezza è_" + input_str);
				System.out.println("La richiesta GET è_" + richiesta);
				if (richiesta != null && richiesta.equals("stop")) {
					stop = true;
				}do {
					str=br.readLine();
					System.out.println(str);
					
					
				}while (str!=null && !str.contentEquals(""));
				if(input_split[0].contentEquals("POST")) {
					
					char[] ch_arr=new char[13];
					br.read(ch_arr);
					str=ch_arr.toString();
					for(int i=0;i<13;i++) 
						System.out.println(ch_arr[i]);
					System.out.println("Content"+str);
					
				}
					
				// System.out.println("La richiesta è_"+input_str);
				// OutputStream out=connessione.getOutputStream();

//				System.out.println("Richiesta non elaborata");
//				System.out.println("Server stop alla porta 8080");
				Risposta risp = new Risposta(connessione.getOutputStream());// oggetto risposta
				risp.sendSource(richiesta);// manda una serie di dati al client. i dati sono in Risposta e sono a
											// prescindere dalla richiesta
				br.close();
				connessione.close();
			} // finisce il while

			System.out.println("Server shutdown");

			server.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
