package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.Data;
import data.EmptyDatasetException;
import mining.ClusteringRadiusException;
import mining.QTMiner;

public class ServerOneClient extends Thread {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private QTMiner qt;

	/*
	 * Costruttore di classe. Inizializza gli attributi socket, in e out. Avvia il
	 * thread
	 */
	public ServerOneClient(final Socket s) throws IOException {
		socket = s;
		in = new ObjectInputStream(socket.getInputStream());

		out = new ObjectOutputStream(socket.getOutputStream());

		/*
		 * se una qualsiasi delle chiamate precedenti solleva una eccezione, il processo
		 * chiamante è responsabile della chiusura del socket. Altrimenti lo chiuderà il
		 * thread
		 */
		start(); // Chiama run()
	}

	/*
	 * Riscrive il metodo run della superclasse Thread al fine di gestire le
	 * richieste del client
	 */
	@Override
	public void run() {
		Data data = null;
		String nome = null;
		double raggio = 0;
		try {
			while (true) {
				int inn = (int) in.readObject();
				switch (inn) {
				case 0:
					///
					nome = (String) in.readObject();
					///
					data = new Data(nome);
					out.writeObject("OK");
					break;
				case 1:
					////
					raggio = (double) in.readObject();
					////
					qt = new QTMiner(raggio);
					out.writeObject("OK");
					out.writeObject(qt.compute(data));
					out.writeObject(qt.toString());
					break;
				case 2:
					qt.salva(nome + raggio + ".dmp");
					out.writeObject("OK");
					break;
				case 3:
					qt = new QTMiner((String) in.readObject() + (double) in.readObject() + ".dmp");
					out.writeObject("OK");
					out.writeObject(qt.toString());
					break;
				}
			}
		} catch (IOException e) {
			System.err.println("IO Exception");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClusteringRadiusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyDatasetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}

}
