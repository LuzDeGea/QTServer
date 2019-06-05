package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	private int PORT = 8080;

	// istanzia un oggetto di tipo MultiServer
	public static void main(final String[] args) throws IOException {
		MultiServer ms = new MultiServer(8080);
	}

	// Costruttore di classe. Inizializza la porta ed invoca run()
	public MultiServer(final int port) {
		PORT = port;
		run();
	}

	/*
	 * Istanzia un oggetto istanza della classe ServerSocket che pone in attesa di
	 * crichiesta di connessioni da parte del client. Ad ogni nuova richiesta
	 * connessione si istanzia ServerOneClient.
	 */
	private void run() {
		ServerSocket s = null;
		try {
			s = new ServerSocket(PORT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Server Started");

		try {
			while (true) {
				// Si blocca finchè non si verifica una connessione:
				try {
					Socket socket = s.accept();
					new ServerOneClient(socket);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		} finally

		{
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
