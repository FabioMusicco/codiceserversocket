package serversocket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Car;

public class Risposta {

	private OutputStream out;

	public Risposta(OutputStream out) {
		this.out = out;
	}

	// sarebbe un flusso scrittore ma è un flusso lettore. in risposta mi da la
	// stessa cosa
	public void sendSource(String richiesta) {//prende la lista car
//		SessionFactory sf=(SessionFactory) new Configuration().configure("hibernate.cfg.xml");
//		Session em=sf.openSession();
//		List<Car> cars_db=(List<Car>)em.createQuery("select car from main.java.entities.Car car", Car.class).getResultList();
//		System.out.println("List"+cars_db);
//		em.close();
//		sf.close();
		File file = new File(SocketServer.risorse+richiesta);// + errore. Sono sul file
		int lunghezza_file=(int)file.length();//leggere qualunque file
		//BufferedOutputStream brout=null;
		PrintWriter pr=new PrintWriter(out);
		setHeaders(richiesta,pr);
//		byte[] dati=null;
		if (richiesta.contentEquals("cars.html"))
		{
			carsList(pr);
			pr.flush();
		}else if(fileExist(file)) {//invio content HTTp
			try {
				inviaFile(file, lunghezza_file);
				}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		pr.close();
	}
		//String errore="errore.html";
		private void inviaFile(File file, int lunghezza_file) throws FileNotFoundException, IOException {
			BufferedOutputStream brout;
			byte[] dati=null;
			brout=new BufferedOutputStream(out);
			dati=new byte[lunghezza_file];
			FileInputStream file_input=new FileInputStream(file);
			file_input.read(dati);//letto il file e parchegiato come array di byte all interno di "dati"
			brout.write(dati,0,lunghezza_file);
			brout.flush();
			brout.close();
			file_input.close();
//		PrintWriter pr = new PrintWriter(out);
//		pr.println("HTTP/1.1 200 OK");
//		pr.println("Servere:Mio Server");
//		pr.println("Date: " + new Date());
		}
		
		private boolean fileExist(File file) {
			return file.exists() && file.isFile();
		}
		
		private void setHeaders(String richiesta, PrintWriter pr) {
			//PrintWriter pr = new PrintWriter(out);
			pr.println("HTTP/1.1 200 OK");
			pr.println("Servere:Mio Server");
			pr.println("Date: " + new Date());
			
		if(richiesta.endsWith(".html") || richiesta.endsWith(".htm")) {
			pr.println("Content-type: text/html");
			
		}else {
			pr.println("Content-type: text/plain");
		}
		pr.println();
		pr.flush();
		}
		
		private void carsList(PrintWriter pr) {
			
			
			
		}
		
		
}

		
//		BufferedReader br = new BufferedReader(new FileReader(file));
		
		
		
		
		
//			if(richiesta.equals("button.html")) {
//				nome_file=SocketServer.risorse + errore;
//				}
//				//File file = new File(nome_file);
//				
//	//			System.out.println(file);
//				
//				BufferedReader	br =null;
//				
//			else {
//			//	File file = new File(SocketServer.risorse + errore);
//				System.out.println(br_error);
//			}
//			System.out.println(br.readLine());
//			br.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
////		BufferedReader br_button = null;
//
//		PrintWriter pr = new PrintWriter(out);
////		pr.println("HTTP/1.1 200 OK");
////		pr.println("Servere:Mio Server");
////		pr.println("Date: " + new Date());
////		pr.println("Content-type: text/html");
//		pr.println();// fine degli headers
//		
//		brout.write(dati,0,lunghezza_file);//invio i dati 
////		pr.println("<!DOCTYPE html>");// inizia il documento html
////		pr.println("<html>");
////		pr.println("<body>");
////		pr.println("<h1>My First Heading</h1>");
////		pr.println("<p>My first paragraph.</p>");
////		pr.println("</body>");
////		pr.println("</html>");
//		pr.flush();//
//	}
//
//}
